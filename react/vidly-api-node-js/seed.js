const {Genre} = require("./models/genre");
const {Movie} = require("./models/movie");
const mongoose = require("mongoose");

const data = [
    {
        name: "Comedy",
        movies: [
            {title: "Airplane", numberInStock: 5, dailyRentalRate: 2},
            {title: "The Hangover", numberInStock: 10, dailyRentalRate: 2},
            {title: "Wedding Crashers", numberInStock: 15, dailyRentalRate: 2}
        ]
    },
    {
        name: "Action",
        movies: [
            {title: "Die Hard", numberInStock: 5, dailyRentalRate: 2},
            {title: "Terminator", numberInStock: 10, dailyRentalRate: 2},
            {title: "The Avengers", numberInStock: 15, dailyRentalRate: 2}
        ]
    },
    {
        name: "Romance",
        movies: [
            {title: "The Notebook", numberInStock: 5, dailyRentalRate: 2},
            {title: "When Harry Met Sally", numberInStock: 10, dailyRentalRate: 2},
            {title: "Pretty Woman", numberInStock: 15, dailyRentalRate: 2}
        ]
    },
    {
        name: "Thriller",
        movies: [
            {title: "The Sixth Sense", numberInStock: 5, dailyRentalRate: 2},
            {title: "Gone Girl", numberInStock: 10, dailyRentalRate: 2},
            {title: "The Others", numberInStock: 15, dailyRentalRate: 2}
        ]
    }
];

async function seed() {

    const ssl = process.env.MONGODB_SSL === "true";
    const useNewUrlParser = process.env.MONGODB_USE_NEW_URL_PARSER === "true";
    const useUnifiedTopology = process.env.MONGODB_USE_UNIFIED_TOPOLOGY === "true";
    const connection = getConnection();
    mongoose.connect(connection, {ssl: ssl, useNewUrlParser: true})
        .then(() => console.log(`Connected to MongoDB !`))
        .catch((err) => console.log("couldnt connect to db..", err));

    await Movie.deleteMany({});
    await Genre.deleteMany({});

    for (let genre of data) {
        const {_id: genreId} = await new Genre({name: genre.name}).save();
        const movies = genre.movies.map(movie => ({
            ...movie,
            genre: {_id: genreId, name: genre.name}
        }));
        await Movie.insertMany(movies);
    }

    await mongoose.disconnect();

    console.info("Done!");
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

seed();
