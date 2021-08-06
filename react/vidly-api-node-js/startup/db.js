const mongoose = require('mongoose');

module.exports = function () {
    const ssl = process.env.MONGODB_SSL === "true";
    const useNewUrlParser = process.env.MONGODB_USE_NEW_URL_PARSER === "true";
    const useUnifiedTopology = process.env.MONGODB_USE_UNIFIED_TOPOLOGY === "true";
    const connection = getConnection();
    mongoose.connect(connection, {ssl, useNewUrlParser, useUnifiedTopology})
        .then(() => console.log("Connected to MongoDB !"))
        .catch((err) => console.log("couldnt connect to db..", err));
}

function getConnection() {
    const protocol = process.env.MONGODB_PROTOCOL;
    const cluster = process.env.MONGODB_CLUSTER;
    const database = process.env.MONGODB_DATABASE;
    const auth = process.env.MONGODB_AUTH;
    const username = process.env.MONGODB_USERNAME;
    const password = process.env.MONGODB_PASSWORD;
    return `${protocol}://${username}:${password}@${cluster}/${database}?authSource=${auth}`
}