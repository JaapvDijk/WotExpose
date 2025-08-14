const { generateApi } = require('swagger-typescript-api');
const path = require('path');

(async () => {
    try {
        await generateApi({
            name: "WotApi.ts",
            output: path.resolve(process.cwd(), "./src/__generated__"),
            url: 'http://localhost:5000/v3/api-docs',
            httpClientType: "axios",
            cleanOutput: true,
        });
        console.log("API client generated successfully!");
    } catch (err) {
        console.error("API generation failed:", err);
        process.exit(1);
    }
})();