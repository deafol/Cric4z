create table Player (
  id bigint primary key,
  firstname varchar(256),
  lastname varchar,
  prefix varchar,
  initials varchar
)
as select * from csvread('C:\Projecten\web\Cric4z\src\main\resources\player.csv');
