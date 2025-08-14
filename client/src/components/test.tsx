import { Api } from '../__generated__/Api';
import { useQuery } from "@tanstack/react-query";

const api = new Api();

export function UserList() {
  const { data, error, isLoading } = useQuery({
    queryKey: ["playerSearch", "EU", "John"],
    queryFn: () =>
      api.player.search({
        request: { region: "EU", name: "John" }
      }).then(res => res.data),
  });

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Something went wrong</p>;

  const firstNickname = data?.[0]?.nickname ?? "No results";
  return <p>{firstNickname}</p>;
}