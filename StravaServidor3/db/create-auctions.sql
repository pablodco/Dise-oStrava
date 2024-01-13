/* DELETE 'auctions_user' database*/
DROP SCHEMA IF EXISTS stravadb;
/* DELETE USER 'auctions_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'strava_user'@'%';

/* CREATE ''auctionsdb' DATABASE */
CREATE SCHEMA IF NOT EXISTS stravadb;
/* CREATE THE USER 'strava_user' AT LOCAL SERVER WITH PASSWORD 'strava_user' */
CREATE USER IF NOT EXISTS 'strava_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'stravadb' FOR THE USER 'auctions_user' AT LOCAL SERVER*/
GRANT ALL ON stravadb.* TO 'strava_user'@'%';