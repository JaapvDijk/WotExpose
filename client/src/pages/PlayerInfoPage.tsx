import { useParams } from "react-router-dom";
import { Box, Card, CardContent, Typography, Skeleton } from "@mui/material";
import { Api, PlayerRequest } from '../__generated__/Api';
import { useQuery } from "@tanstack/react-query";
import Grid from '@mui/material/Grid';
import { timestampToDate } from "../utils/DateUtils";
import { getPercentage, getWinrateVerdict } from '../utils/StatUtils';

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

    const info = infoResult?.data?.data;
    const tanks = tanksResult?.data?.data;
    
    const winrate = getPercentage(info?.statistics?.all?.battles, info?.statistics?.all?.wins);
    return (
        <>
            <Grid container padding={2} spacing={2} alignItems="center">
            {infoResult.isLoading ? (
                <Skeleton variant="rectangular" height={100} />
            ) : infoResult.isError ? (
                <CardContent>
                Could not load player info: {(infoResult.error as Error).message}
                </CardContent>
            ) : info ? (
                <>
                    <Grid container spacing={2} size={{xs:7, md:10}} direction="row" alignItems="center">
                        <Grid>
                            <Typography variant="h5">
                                {info.nickname}#{info.account_id}
                            </Typography>
                        </Grid>

                        <Grid>
                            <Typography variant="h6">
                                Rating: <strong>{info.global_rating}</strong>
                            </Typography>
                        </Grid>

                        <Grid>
                            <Typography variant="h6">
                                Winrate: <strong>{winrate}%</strong>
                            </Typography>
                        </Grid>

                        <Grid size={12}>
                            <Typography variant="body2" textAlign="left">
                                This player performs <b>{getWinrateVerdict(winrate)}</b>, lets see whats under it:
                            </Typography>
                        </Grid>
                    </Grid>
                    
                <Grid size={{xs:5, md:2}}>
                    <Card>
                        <Typography variant="caption">
                            Registered: {timestampToDate(info.created_at)}
                        </Typography>
                        <br />
                        <Typography variant="caption">
                            Last battle: {timestampToDate(info.last_battle_time)}
                        </Typography>
                    </Card>
                </Grid>
                </>
            ) : (
                <CardContent>No player info</CardContent>
            )}
            </Grid>

            <Grid container spacing={2} gap={2}>
            {tanksResult.isLoading ? (
            <Skeleton variant="rectangular" height={100} />
            ) : tanksResult.isError ? (
                <CardContent>
                    Could not load player info: {(tanksResult.error as Error).message}
                </CardContent>
            ) : tanks ? (
                <> 
                    <Grid size={{xs:12}}>
                        <Card>
                            <CardContent>
                                <Typography variant="body2" color="textSecondary">
                                    tanks: {tanks?.totalBattlesAll}
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
                </>
            ) : (
                <CardContent>No tanks info</CardContent>
            )}
            </Grid>
        </>
    );
}

export default PlayerInfoPage;