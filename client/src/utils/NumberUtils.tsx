import { BaseStatistics } from "../__generated__/Api";

export function getPercentage(total?: number, matches?: number) {
    if (!total || total <= 0) {  
        return undefined;
    }

    return matches && ((matches / total * 100).toFixed(2) + '%');
}