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
      return "below average";
    case winrate <= 49:
      return "average";
    case winrate <= 52:
      return "good";
    case winrate <= 54:
      return "very good";
    case winrate <= 56:
      return "great";
    case winrate < 65:
      return "unicum";
    default:
      return "super unicum";
  }
};