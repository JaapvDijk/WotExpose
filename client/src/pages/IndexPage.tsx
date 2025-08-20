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
import { isValidName } from "../utils/StringUtils";

const api = new Api();

function Banner() {
  const [options, setOptions] = useState<PlayerSearchResponse[]>([]);

  const setData = async (searchTerm: string) => {
    if (searchTerm.length < 3) return;

    const playerSearchRequest: PlayerSearchRequest = { region: "EU", name: searchTerm };

    const response = await api.player.search(playerSearchRequest as any);

    setOptions(response.data ?? []);
  };

  const onInputChange = (event: any, value: string, reason: any) => {
    if (value && isValidName(value)) {
      setData(value);
    } else {
      setOptions([]);
    }
  };

  return (
  <Grid className="home-page">
      <Grid size={{xs:12}}>
      <Box
        className="logo"
        component="img"
        sx={{
          height: 250,
          width: 350,
          objectFit: "cover",
          userSelect: "none",
          pointerEvents: "none",
          mb:2,
        }}
        alt="logo"
        src={WotStats}
      />
        <Typography variant="h6" className="subtitle" >
              Because screw stat padders
        </Typography>
        <Typography variant="subtitle2">
          Insights not on whether a player performs well, but <b>why</b> they're performing well.
        </Typography>
      </Grid>

    <Grid size={{xs:12, sm:12}} sx={{ marginTop: "50px" }}>
      <InputLabel size="medium" disabled>
        Find a player:
      </InputLabel>
      <Autocomplete
        className="search-box"
        disablePortal
        id="find-stores"
        sx={{ width: 500, maxWidth: "90%", margin: "0 auto" }}
        renderOption={(props, option) => (
          <Link
            to={{ pathname: "info/" + option.account_id }}
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
  );
}

export default Banner;
