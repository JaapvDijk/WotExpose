export function timestampToDate(timestamp?: number) {
    if (!timestamp) {
        return undefined;
    }
    
    const date = new Date(timestamp * 1000);

    return date.toLocaleDateString();
}