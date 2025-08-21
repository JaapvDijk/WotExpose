import { 
  Box, 
  Button, 
  Link, 
  Paper, 
  TextField, 
  Typography 
} from "@mui/material";
import Grid from '@mui/material/Grid';
import LoadingButton from '@mui/lab/LoadingButton';
import { Link as RouterLink, useNavigate } from "react-router-dom";
import { useFormik } from "formik";
import * as Yup from "yup";
import { authThunks } from "../redux/auth";
import { useMutation } from "@tanstack/react-query";
import { Api, LoginRequest } from '../__generated__/Api';
 
const api = new Api();

function LoginPage() {
  const navigate = useNavigate();

  const login = useMutation({
    mutationFn: (loginRequest: LoginRequest) => api.auth.authenticate(loginRequest),
    onSuccess: (data) => {
      if (data.data.token)
      {
        authThunks.login(data.data.token+"");
        console.log("TOKEN: " + data.data.token);
        // api.setSecurityData(data.data.token);
        navigate("../admin", { replace: true });
      }
    },
    onError: (error) => {
      console.error("Login failed", error);
    }
  });

  const handleLogin = (email: string, password: string) => {
    const loginRequest: LoginRequest = { email, password };

    login.mutate(loginRequest);
    
    navigate('../admin', { replace: true })
  };

  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema: Yup.object({
      email: Yup.string()
        .email("validation msg")
        .required("required msg"),
      password: Yup.string()
        .min(6, "pass min")
        .required("pass required"),
    }),
    onSubmit: (values) => handleLogin(values.email, values.password),
  });

  return (
    <Grid container component="main" sx={{ height: "calc(100vh - var(--footer-height, 0px))" }}>
      <Grid size={{xs:false, sm:3, md:7}} className="login-page" />
      <Grid size={{xs:12, sm:8, md:5}} component={Paper}>
        <Box sx={{paddingTop:15, display: "flex",
            flexDirection: "column",
            alignItems: "center", background:"linear-gradient(270deg,rgba(12,71,99,0.8), rgba(0,0,0,0.35)"}}>
          <Typography component="h1" variant="h5">
            {"Login"}
          </Typography>
          <Box
            component="form"
            marginTop={3}
            noValidate
            onSubmit={formik.handleSubmit}
          >
            <TextField
              margin="normal"
              variant="filled"
              required
              fullWidth
              id="email"
              label={"email"}
              name="email"
              autoComplete="email"
              autoFocus
              disabled={false}
              value={formik.values.email}
              onChange={formik.handleChange}
              error={formik.touched.email && Boolean(formik.errors.email)}
              helperText={formik.touched.email && formik.errors.email}
            />
            <TextField
              margin="normal"
              variant="filled"
              required
              fullWidth
              name="password"
              label={"password"}
              type="password"
              id="password"
              autoComplete="current-password"
              disabled={false}
              value={formik.values.password}
              onChange={formik.handleChange}
              error={formik.touched.password && Boolean(formik.errors.password)}
              helperText={formik.touched.password && formik.errors.password}
            />
            <Box sx={{ textAlign: "right" }}>
              <Link
                component={RouterLink}
                to={`/${process.env.PUBLIC_URL}/forgot-password`}
                variant="body2"
              >
                {"forgot password"}
              </Link>
            </Box>
            <LoadingButton
              type="submit"
              fullWidth
              loading={false}
              variant="contained"
              sx={{ mt: 3 }}
            >
              {"login"}
            </LoadingButton>
            <Button
              component={RouterLink}
              to={`/${process.env.PUBLIC_URL}/register`}
              color="primary"
              fullWidth
              sx={{ mt: 2 }}
            >
              {"register"}
            </Button>
          </Box>
        </Box>
      </Grid>
    </Grid>
  );
};

export default LoginPage;