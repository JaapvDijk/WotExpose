import { useParams } from "react-router-dom";
import { AppBar, Box, Button, Card, CardContent, Typography, CircularProgress } from "@mui/material";
import { Api, PlayerRequest, WoTPlayerInfoResponse } from '../__generated__/Api';
import { useQuery } from "@tanstack/react-query";
import Grid from '@mui/material/Grid';
import { timestampToDate } from "../utils/DateUtils";
import { useState } from "react";
import { getPercentage } from '../utils/NumberUtils';

const api = new Api();

function PlayerInfoPage() {
    const { id } = useParams();
    const playerId = Number(id);

    const playerRequest: PlayerRequest = { region: "EU" }

    const infoResult = useQuery({
        queryKey: ['info', id],
        queryFn: () => api.player.getInfo(playerId, playerRequest as any),
        enabled: !isNaN(playerId),
    })

    const tanksResult = useQuery({
        queryKey: ['tanks', id],
        queryFn: () => api.player.getTankStats(playerId, playerRequest as any),
        enabled: !isNaN(playerId),
    })

    let info = infoResult?.data?.data;
    let tanks = tanksResult?.data?.data;
    
    return (
        <div>
            <AppBar position="static">
                <Typography variant="h6" style={{ padding: 3 }}>
                    { infoResult.isLoading ? (<CircularProgress size={20} style={{ marginRight: 8 }} />) 
                    : infoResult.isError ? (<>Error: {(infoResult.error as Error).message}</>)
                    : info ? (<>
                        {info.nickname}#{info.account_id} - Rating: {info.global_rating} - 
                        Winrate: {getPercentage(info.statistics?.all?.battles, info.statistics?.all?.wins)}
                        <br/>Registered: {timestampToDate(info.created_at)} Last battle: {timestampToDate(info.last_battle_time)}
                    </>)
                    : ("No player info") }
                </Typography>
            </AppBar>

            <Grid container spacing={2} style={{ padding: 20 }}>
                <Grid size={{xs:12}}>
                    <Card>
                        <CardContent>
                            <Typography variant="body2" color="textSecondary">
                                tanks: { tanks?.first?.all?.explosion_hits }
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid size={{xs:12, sm:8}}>
                    <Card>
                        <Typography variant="h6">
                            Lorem
                        </Typography>
                    </Card>
                </Grid>

                <Grid size={{xs:12, sm:4}}>
                    <Box>
                        <Typography variant="h6">
                            Lorem
                        </Typography>
                        <Typography variant="body2" color="textSecondary">
                            Ipsum
                        </Typography>
                    </Box>
                </Grid>
            </Grid>
        </div>
    );
}

export default PlayerInfoPage;