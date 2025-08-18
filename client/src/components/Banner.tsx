import {
  Autocomplete,
  Container,
  InputLabel,
  ListItem,
  ListItemText,
  TextField,
  Typography
} from "@mui/material";
import Grid from '@mui/material/Grid'
import { Link } from "react-router-dom";
import { useState } from "react";
import { PlayerSearchResponse } from "../__generated__/Api";
import Box from "@mui/material/Box";
import WotStats from "../images/wotstats.png";
import { PlayerSearchRequest } from "../__generated__/Api";
import { useQuery } from "@tanstack/react-query";
import { Api } from "../__generated__/Api";

const api = new Api();

function Banner() {
  const [options, setOptions] = useState<PlayerSearchResponse[]>([]);

  const setData = async (searchTerm: string) => {
    const controller = new AbortController();
    const signal = controller.signal;

    const playerSearchRequest: PlayerSearchRequest = { region: "EU", name: "Johnd" };

    const response = await api.player.search(playerSearchRequest as any);
    setOptions(response.data ?? []);

  };

  const onInputChange = (event: any, value: string, reason: any) => {
    if (value) {
      setData(value);
    } else {
      setOptions([]);
    }
  };

  return (
  <Container
    sx={{
      paddingBottom: "75px",
      backgroundAttachment: "fixed",
      backgroundImage: `
        radial-gradient(circle at 30% 40%, rgba(60, 60, 80, 0.6) 0%, rgba(30, 30, 45, 0.8) 40%, rgba(15, 15, 25, 0.95) 100%),
        radial-gradient(circle at 70% 60%, rgba(90, 70, 100, 0.3) 0%, transparent 50%),
        linear-gradient(180deg, rgba(10, 10, 20, 0.4), rgba(0, 0, 0, 0.8))
      `,
      backgroundColor: "rgb(10, 10, 15)"
    }}
  >
      <Grid container sx={{ paddingTop: "70px" }} spacing={2}>
        {/* first row */}
        <Grid size={{xs:12}}>
        <Box
          component="img"
          sx={{
            height: 250,
            width: 350,
            borderRadius: 2,
            objectFit: "cover",
            userSelect: "none",
            pointerEvents: "none"
          }}
          alt="logo"
          src={WotStats}
        />
          <Typography variant="h6" sx={{userSelect: "none", pointerEvents: "none"}}>Because screw stat padders</Typography>
          <Typography variant="subtitle2" sx={{userSelect: "none", pointerEvents: "none"}}>
            Insights not on whether a player performs well, but <b>why</b> they're performing well.
          </Typography>
        </Grid>

        {/* second row - middle*/}
        <Grid size={{xs:12, sm:12}} sx={{ marginTop: "75px" }}>
          <InputLabel size="medium" disabled>
            Find a player:
          </InputLabel>
          <Autocomplete
            disablePortal
            id="find-stores"
            sx={{ width: 500, maxWidth: "90%", margin: "0 auto" }}
            renderOption={(props, option) => (
              <Link
                to={{ pathname: "Store/" + option.account_id }}
                state={option}
                style={{
                  textDecoration: "none",
                  color: "inherit",
                  fontSize: "15px"
                }}
              >
                <ListItem {...props} key={option.account_id}>
                  <ListItemText
                    primary={`${option.nickname} (${option.account_id})`}
                  />
                </ListItem>
              </Link>
            )}
            renderInput={(params) => (
              <TextField {...params} label="Player name" />
            )}
            options={options}
            filterOptions={(x) => x}
            onInputChange={onInputChange}
          />
        </Grid>
      </Grid>
    </Container>
  );
}

export default Banner;