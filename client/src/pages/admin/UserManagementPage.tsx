import {
  Container,
  Typography,
  Button,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TablePagination,
  IconButton,
  Skeleton,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { ApiClient, UserIsAdmin } from "../../ApiClient";
import { RootState } from "../../redux/store";
import { useEffect, useState } from "react";
import { UserRequest, UserResponse } from "../../__generated__/Api";
import { confirm } from "../../components/Confirm";
import { useQuery, useMutation, useQueryClient, keepPreviousData } from "@tanstack/react-query";

const privateApi = ApiClient.getAuthInstance();

function UserManagementPage() {
  const token = useSelector((state: RootState) => state.auth.token);
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(20);

  const [openDialog, setOpenDialog] = useState(false);
  const [editingUser, setEditingUser] = useState<UserResponse | null>(null);
  const [formData, setFormData] = useState<{ fullName: string; email: string }>({
    fullName: "",
    email: "",
  });

  useEffect(() => {
    if (!UserIsAdmin(token)) {
      navigate("/login", { replace: true });
    }
  }, [token, navigate]);

  const {
    data,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ["users", page, rowsPerPage],
    queryFn: async () => {
      const res = await privateApi.user.getUsers({
        pageNr: page,
        amount: rowsPerPage,
      });
      return res.data;
    },
    placeholderData: keepPreviousData, 
  });

  const users = data?.content || [];
  const totalUsers = data?.totalElements || 0;

  const createUserMutation = useMutation({
    mutationFn: (newUser: UserRequest) =>
      privateApi.auth.register({ ...newUser, password: "Default123!" }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });

  const updateUserMutation = useMutation({
    mutationFn: ({ id, payload }: { id: number; payload: UserRequest }) =>
      privateApi.user.updateUser(id, payload),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });

  const deleteUserMutation = useMutation({
    mutationFn: (id: number) => privateApi.user.deleteUser(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });

  const handleOpenDialog = (user?: UserResponse) => {
    if (user) {
      setEditingUser(user);
      setFormData({ fullName: user.fullName || "", email: user.email || "" });
    } else {
      setEditingUser(null);
      setFormData({ fullName: "", email: "" });
    }
    setOpenDialog(true);
  };

  const handleCloseDialog = () => setOpenDialog(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSave = async () => {
    try {
      if (editingUser) {
        await updateUserMutation.mutateAsync({
          id: editingUser.id!,
          payload: formData as UserRequest,
        });
      } else {
        await createUserMutation.mutateAsync(formData as UserRequest);
      }
      handleCloseDialog();
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (id: number, fullName: string) => {
    const confirmed = await confirm({
      message: "Are you sure you want to delete user " + fullName + "?",
    });
    if (!confirmed) return;

    try {
      await deleteUserMutation.mutateAsync(id);
    } catch (error) {
      console.error(error);
    }
  };

  const handleChangePage = (_event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <Container sx={{ minHeight: 'calc(100vh - var(--footer-height, 0px))', background: "linear-gradient(180deg, #1f1f1f, #121212)", p: 3 }}>
      <Typography variant="h4" gutterBottom>
        User Management
      </Typography>

      <Button variant="contained" color="primary" onClick={() => handleOpenDialog()} sx={{ mb: 2 }}>
        Add User
      </Button>

      {isLoading ? (
        <Skeleton variant="rectangular" height={400} />
      ) : isError ? (
        <Typography color="error">Failed to load users.</Typography>
      ) : (
        <>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Full Name</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {users.map((user) => (
                <TableRow key={user.id}>
                  <TableCell>{user.id}</TableCell>
                  <TableCell>{user.fullName}</TableCell>
                  <TableCell>{user.email}</TableCell>
                  <TableCell>
                    <IconButton color="primary" onClick={() => handleOpenDialog(user)}>
                      <EditIcon />
                    </IconButton>
                    <IconButton color="error" onClick={() => handleDelete(user.id!, user.fullName!)}>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>

          <TablePagination
            component="div"
            count={totalUsers}
            page={page}
            onPageChange={handleChangePage}
            rowsPerPage={rowsPerPage}
            onRowsPerPageChange={handleChangeRowsPerPage}
            rowsPerPageOptions={[5, 10, 20, 100]}
          />
        </>
      )}

      <Dialog open={openDialog} onClose={handleCloseDialog}>
        <DialogTitle>{editingUser ? "Edit User" : "Add User"}</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Full Name"
            name="fullName"
            value={formData.fullName}
            onChange={handleChange}
            fullWidth
          />
          <TextField
            margin="dense"
            label="Email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Cancel</Button>
          <Button onClick={handleSave} variant="contained" color="primary">
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
}

export default UserManagementPage;