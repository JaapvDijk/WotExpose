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
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { ApiClient, UserIsAdmin } from "../ApiClient";
import { RootState } from "../redux/store";
import { useEffect, useState } from "react";
import { UserRequest, UserResponse, PageUserResponse } from "../__generated__/Api";
import { confirm } from '../components/Confirm';

const privateApi = ApiClient.getAuthInstance();

export default function AdminUserPage() {
  const token = useSelector((state: RootState) => state.auth.token);
  const navigate = useNavigate();

  const [users, setUsers] = useState<UserResponse[]>([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(20);
  const [totalUsers, setTotalUsers] = useState(0);

  const [openDialog, setOpenDialog] = useState(false);
  const [editingUser, setEditingUser] = useState<UserResponse | null>(null);
  const [formData, setFormData] = useState<{ fullName: string; email: string }>({
    fullName: "",
    email: "",
  });

  // Redirect non-admins
  useEffect(() => {
    if (!UserIsAdmin(token)) {
      navigate("/login", { replace: true });
    }
  }, [token, navigate]);

  // Load users
  const fetchUsers = async () => {
    setLoading(true);
    try {
      const res = await privateApi.user.getUsers({ pageNr: page, amount: rowsPerPage });
      setUsers(res.data?.content || []);
      setTotalUsers(res.data?.totalElements || 0);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, [page, rowsPerPage]);

  // Open dialog for new or edit user
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
        await privateApi.user.updateUser(editingUser.id!, formData as UserRequest);
      } else {
        await privateApi.auth.register({ ...formData, password: "Default123!" });
      }
      fetchUsers();
      handleCloseDialog();
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (id: number) => {
    const confirmed = await confirm({ message: 'Are you sure you want to delete this user?' });
    if (!confirmed) return;
    
    try {
      await privateApi.user.deleteUser(id);
      fetchUsers();
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
    <Container sx={{ background: 'linear-gradient(180deg, #1f1f1f, #121212)', padding: 4 }}>
      <Typography variant="h4" gutterBottom>
        User Management
      </Typography>

      <Button variant="contained" color="primary" onClick={() => handleOpenDialog()} sx={{ mb: 2 }}>
        Add User
      </Button>

      {loading ? (
        <Skeleton variant="rectangular" height="100%" />
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
                    <IconButton color="error" onClick={() => handleDelete(user.id!)}>
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
            rowsPerPageOptions={[5, 10, 20, 50]}
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