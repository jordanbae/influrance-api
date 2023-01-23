require("dotenv").config();
var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
const cors = require("cors");
const jwt = require("jsonwebtoken");

//Mongoose
const mongoose = require("mongoose");
mongoose.Promise = global.Promise;
mongoose
  .connect(process.env.DB_MONGOOSE_CREDENTIALS)
  .then(() => console.log("connected"))
  .catch((err) => console.error(err));
// ------------------------------------- //
//My Routes
const influencerRouter = require("./routes/influencer");
const policyRouter = require("./routes/policy");
const purchaseRouter = require("./routes/purchase");
//Auth
const authRouter = require("./routes/auth");
// ------------------------------------- //
var indexRouter = require("./routes/index");
var usersRouter = require("./routes/users");

var app = express();
const verifyToken = (req, res, next) => {
  const bearerHeader = req.headers["authorization"];
  if (typeof bearerHeader !== "undefined") {
    const bearer = bearerHeader.split(" ");
    const bearerToken = bearer[1];
    req.token = bearerToken;
  }

  jwt.verify(req.token, process.env.JWT_SECRET, (err, decodedUser) => {
    if (err || !decodedUser)
      return res.status(401).json({ error: "Unauthorized Request" });

    req.user = decodedUser;

    next();
  });
};
// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "jade");

//add cors options to allow credentials when using axios to make generate cookie work
const corsOptions = {
  credentials: true,
};
app.use(cors(corsOptions));

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

app.use("/api", indexRouter);
app.use("/api/users", verifyToken, usersRouter);
// app.use my routes
app.use("/api/influencers", verifyToken, influencerRouter);
app.use("/api/policies", policyRouter);
app.use("/api/purchases", purchaseRouter);
app.use("/api/auth", authRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render("error");
});

module.exports = app;
