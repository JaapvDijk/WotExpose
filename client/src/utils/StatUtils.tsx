export function getPercentage(total?: number, matches?: number): number {
  if (!total || total <= 0 || !matches) {  
    return 0;
  }

  return Number(((matches / total) * 100).toFixed(2));
}

export const getWinrateVerdict = (winrate?: number): string => {
  if (!winrate || winrate <= 0) return "unknown";

  switch (true) {
    case winrate <= 46:
      return "very bad";
    case winrate <= 47:
      return "bad";
    case winrate <= 48:
      return "below average";
    case winrate <= 49:
      return "average";
    case winrate <= 51:
      return "above average";
    case winrate <= 53:
      return "good";
    case winrate <= 55:
      return "very good";
    case winrate <= 59:
      return "great";
    case winrate <= 65:
      return "unicum";
    case winrate > 65 && winrate <= 70:
      return "super unicum"
    default:
      return "miraculously";
  }
};

export const getWinrateXVMColour = (winrate?: number): string => {
  if (!winrate || winrate <= 0) return "unknown";

  switch (true) {
    case winrate <= 46:
      return "#930D0C";
    case winrate <= 47:
      return "#CD3333";
    case winrate <= 48:
      return "#CD7A00";
    case winrate <= 49:
      return "#CCB801";
    case winrate <= 51:
      return "#849C24";
    case winrate <= 53:
      return "#4D7326";
    case winrate <= 55:
      return "#409ABF";
    case winrate <= 59:
      return "#3A72C7";
    case winrate < 65:
      return "#793EB6";
    case winrate >= 65 && winrate < 70:
      return "#4C157D"
    default:
      return "#E1E2DE";
  }
};