import { useParams } from "react-router-dom";
import { Box, Card, CardContent, Typography, Skeleton, Divider } from "@mui/material";
import { Api, PlayerRequest } from '../__generated__/Api';
import { useQuery } from "@tanstack/react-query";
import Grid from '@mui/material/Grid';
import { timestampToDate } from "../utils/DateUtils";
import { getPercentage, getWinrateVerdict, getWinrateXVMColour } from '../utils/StatUtils';

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
        <Box sx={{ minHeight: 'calc(100vh - var(--footer-height, 0px))', background: 'linear-gradient(180deg, #1f1f1f, #121212)', p: 3 }}>
            <Grid container padding={2} sx={{ minHeight: 125 }}>
            {infoResult.isLoading ? (
                <Grid size={{xs:12}}>
                    <Skeleton variant="rectangular" height="100%" />
                </Grid>
            ) : infoResult.isError ? (
                <Grid size={{xs:12}}>
                    Could not load player info: {(infoResult.error as Error).message}
                </Grid>
            ) : info ? (
                <>
                    <Grid container spacing={2} size={{xs:12, md:9}} direction="row" alignItems="center" 
                    sx={{width:'100%', textAlign:'left', p: 3, mb: 1, borderRadius: 2, background: 'linear-gradient(135deg, #0c4763 0%, #292977 100%)', color: 'white'}}>
                        <Grid>
                            <Typography variant="h6" fontWeight="bold">
                            {info.nickname}#{info.account_id}
                            </Typography>
                        </Grid>

                        <Grid>
                            <Typography variant="subtitle1">
                            Rating: <strong>{info.global_rating}</strong> | Winrate: <strong>{winrate}%</strong>
                            </Typography>
                        </Grid>

                        <Grid size={12}>
                            <Typography variant="body1" textAlign="left">
                                This player performs <b style={{ textShadow: `0 0 0.6px black`, color: getWinrateXVMColour(winrate) }}>{getWinrateVerdict(winrate)}</b>, lets see why
                            </Typography>
                        </Grid>
                    </Grid>
                    
                    <Grid size={{xs:12, md:3}} sx={{ textAlign: { xs: 'center', md: 'right' }}}>
                        <Typography variant="caption">
                            Registered: {timestampToDate(info.created_at)}
                        </Typography>
                        <br />
                        <Typography variant="caption">
                            Last battle: {timestampToDate(info.last_battle_time)}
                        </Typography>
                    </Grid>
                </>
            ) : (
                <CardContent>No player info</CardContent>
            )}
            </Grid>

            <Divider sx={{ my: 2 }} />

            <Grid container spacing={2} sx={{ minHeight: 200 }}>
            {tanksResult.isLoading ? (
                <Grid size={{xs:12}}>
                    <Skeleton variant="rectangular" height="100%" />
                </Grid>
            ) : tanksResult.isError ? (
                <Grid size={{xs:12}}>
                    Could not load player tank stats: {(tanksResult.error as Error).message}
                </Grid>
            ) : tanks ? (
                <> 
                    <Grid size={{xs:12}} sx={{ textAlign: 'left' }}>
                        <Card>
                            <CardContent>
                                <Typography variant="body2" color="textSecondary">
                                    total: {tanks?.totalBattlesAll} <br/>
                                    clan: {tanks?.totalBattlesClan} <br/>
                                    company: {tanks?.totalBattlesCompany}  <br/>
                                    team: {tanks?.totalBattlesTeam}  <br/>
                                    regularteam: {tanks?.totalBattlesRegularTeam}  <br/>
                                    globalmap: {tanks?.totalBattlesGlobalmap}  <br/>
                                    strongholddef: {tanks?.totalBattlesStrongholdDefense}  <br/>
                                    strongholdskirmish: {tanks?.totalBattlesStrongholdSkirmish}  <br/>
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                </>
            ) : (
                <CardContent>No tanks info</CardContent>
            )}
            </Grid>
        </Box>
    );
}

export default PlayerInfoPage;