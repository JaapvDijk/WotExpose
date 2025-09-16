import {
  Container,
  Typography,
  Card,
  CardContent,
  CardActionArea,
  Skeleton,
  InputLabel,
  Select,
  MenuItem,
  FormControl,
  Button,
} from "@mui/material";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { ApiClient, UserIsAdmin } from "../../ApiClient";
import { RootState } from "../../redux/store";
import { useEffect, useState } from "react";
import {useMutation, useQuery } from "@tanstack/react-query";
import Box from "@mui/material/Box";

const privateApi = ApiClient.getAuthInstance();

function AdminDashboardPage() {
  const token = useSelector((state: RootState) => state.auth.token);
  const navigate = useNavigate();
  const [region, setRegion] = useState<"EU" | "NA" | "ASIA">("EU");

  useEffect(() => {
    if (!UserIsAdmin(token)) {
      navigate("/login", { replace: true });
    }
  }, [token, navigate]);

  const user = useQuery({
    queryKey: ["users", "count"],
    queryFn: async () => {
      const res = await privateApi.user.getUsers({ pageNr: 0, amount: 1 }); 
      return res.data?.totalElements ?? 0;
    }
  });

  const importMutation = useMutation({
    mutationFn: async () => {
      const res = await privateApi.admin.doImportVehicles({region});
      return res.data;
    },
    onSuccess: (data) => {
      console.log("Import successful:", data);
    },
  });

  return (
    <Container
      sx={{ background: "linear-gradient(180deg, #1f1f1f, #121212)", p: 3 }}>
      <Typography variant="h4" gutterBottom color="white">
        Admin Dashboard
      </Typography>

      <Box sx={{
            display: "flex",
            gap: 2,
            flexWrap: "wrap",
          }}>
        <Card
          sx={{
            width: 200,
            borderRadius: 3,
            boxShadow: 3,
            background: "linear-gradient(135deg, #1976d2, #1565c0)",
            color: "white",
          }}>
          <CardActionArea onClick={() => navigate("/admin/userManagement")}>
            <CardContent sx={{ textAlign: "center" }}>
              <Typography variant="h6">Total Users</Typography>
              {user.isLoading ? (
                <Skeleton variant="text" width={80} sx={{ mx: "auto" }} />
              ) : (
                <Typography variant="h3" fontWeight="bold">
                  {user.data}
                </Typography>
              )}
            </CardContent>
          </CardActionArea>
        </Card>

        <Card
            sx={{
              width: 300,
              borderRadius: 3,
              boxShadow: 3,
              background: "linear-gradient(135deg, #1976d2, #1565c0)",
              color: "white",
            }}>
          <CardContent sx={{ textAlign: "center" }}>
            <Typography variant="h6" gutterBottom>
              Vehicle Import
            </Typography>

            <Box>
              <FormControl sx={{ mr:2}}>
                <InputLabel id="region-select-label">Region</InputLabel>
                <Select
                    labelId="region-select-label"
                    value={region}
                    label="Region"
                    onChange={(e) => setRegion(e.target.value)}>
                  <MenuItem value="EU">EU</MenuItem>
                  <MenuItem value="NA">NA</MenuItem>
                  <MenuItem value="ASIA">ASIA</MenuItem>
                </Select>
              </FormControl>

              <Button
                  variant="contained"
                  color="secondary"
                  onClick={() => importMutation.mutate()}
                  disabled={importMutation.isPending}>
                {importMutation.isPending ? "Importing.." : "Start"}
              </Button>
            </Box>
          </CardContent>
        </Card>
      </Box>

    </Container>
  );
}

export default AdminDashboardPage;