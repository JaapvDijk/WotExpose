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

function Banner() {
  const [options, setOptions] = useState<PlayerSearchResponse[]>([]);

  const setData = (searchTerm: string) => {
    const controller = new AbortController();
    const signal = controller.signal;

    //Fill setOptions here
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
        backgroundImage:
          "repeating-radial-gradient(circle at 0 0, transparent 0, rgba(229, 229, 247, .18) 200px), repeating-linear-gradient(rgba(183, 168, 146, .9), rgba(183, 168, 146, .1))",
        backgroundColor: "rgba(229, 229, 247, 1)"
      }}
    >
      <Grid container sx={{ paddingTop: "70px" }} spacing={2}>
        {/* first row */}
        <Grid size={{xs:12}}>
          <Typography variant="h4">Hello</Typography>
          <Typography variant="subtitle1">
            Get an SMS, a WhatsApp, Messenger, Telegram notification, or an
            email on your phone when your favorites Too Good To Go magic bags
            are available
          </Typography>
        </Grid>

        {/* second row - left */}
        <Grid size={{xs:12, sm:6}} sx={{ marginTop: "75px" }}>
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
              <TextField {...params} label="Address, city, store" />
            )}
            options={options}
            filterOptions={(x) => x}
            onInputChange={onInputChange}
          />
        </Grid>

        {/* second row - right */}
        <Grid size={{xs:12, sm:6}} sx={{ marginTop: "75px" }}>
        </Grid>
      </Grid>
    </Container>
  );
}

export default Banner;