import {
  Container,
  Typography,
  Card,
  CardContent,
  CardActionArea,
  Skeleton,
} from "@mui/material";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { ApiClient, UserIsAdmin } from "../../ApiClient";
import { RootState } from "../../redux/store";
import { useEffect } from "react";
import { useQuery } from "@tanstack/react-query";

const privateApi = ApiClient.getAuthInstance();

function AdminDashboardPage() {
  const token = useSelector((state: RootState) => state.auth.token);
  const navigate = useNavigate();

  useEffect(() => {
    if (!UserIsAdmin(token)) {
      navigate("/login", { replace: true });
    }
  }, [token, navigate]);

  const { data, isLoading } = useQuery({
    queryKey: ["users", "count"],
    queryFn: async () => {
      const res = await privateApi.user.getUsers({ pageNr: 0, amount: 1 }); 
      return res.data?.totalElements ?? 0;
    },
  });

  return (
    <Container
      sx={{ background: "linear-gradient(180deg, #1f1f1f, #121212)", p: 3 }}>
      <Typography variant="h4" gutterBottom color="white">
        Admin Dashboard
      </Typography>

      <Card
        sx={{
          maxWidth: 300,
          borderRadius: 3,
          boxShadow: 3,
          background: "linear-gradient(135deg, #1976d2, #1565c0)",
          color: "white",
        }}>
        <CardActionArea onClick={() => navigate("/userManagement")}>
          <CardContent sx={{ textAlign: "center" }}>
            <Typography variant="h6">Total Users</Typography>
            {isLoading ? (
              <Skeleton variant="text" width={80} sx={{ mx: "auto" }} />
            ) : (
              <Typography variant="h3" fontWeight="bold">
                {data}
              </Typography>
            )}
          </CardContent>
        </CardActionArea>
      </Card>
    </Container>
  );
}

export default AdminDashboardPage;