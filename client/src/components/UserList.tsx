import { Api } from '../__generated__/Api';
import { useQuery } from "@tanstack/react-query";
import { PlayerSearchRequest } from '../__generated__/Api';

const api = new Api();

// export function UserList() {
//   const playerSearchRequest: PlayerSearchRequest = {
//     region: "EU",
//     name: "Johnd",
//   };

//   const { data, error, isLoading } = useQuery({
//     queryKey: ['playerSearcdddh', 'EU', 'JohnE'],
//     queryFn: () => api.player.search(playerSearchRequest as any).then(res => res.data)
//   });

//   if (isLoading) return <p>Loading...</p>;
//   if (error) return <p>Something went wrong</p>;

//   return <p>{data?.[4]?.nickname ?? "No results"}</p>;
// }
