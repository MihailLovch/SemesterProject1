-- insert admin --
insert into users (nickname, email, sex, weight, height, date_of_birth, password)
values ('admin', 'admin', true, 80, 180, now(), 'ad5419d0be9cd40bf9c854580d8a5daf');

-- return last 7 user statistic  --

select users.email, s.calorie, s.fat, s.proteins, s.carb, s.last_modified
from (users
         inner join user_statistic us on users.id = us.user_id
         inner join statistic s on us.statistic_id = s.id)
where users.nickname = 'admin'
order by s.last_modified desc
limit 7;


-- check that it was updated today
select extract(days from now()) <= extract(days from s.last_modified) as bool
from (users
         inner join user_statistic us on users.id = us.user_id
         inner join statistic s on us.statistic_id = s.id)
where users.id = 1
order by s.last_modified desc
limit 1;

select extract(days from now()) > extract(days from '2022-11-10 12:46:54.342538 +00:00'::timestamp);
-- last user stat id  --
select s.calorie, s.proteins, s.carb, s.fat, s.id
from (users
         inner join user_statistic us on users.id = us.user_id
         inner join statistic s on us.statistic_id = s.id)
where users.id = 1
order by s.last_modified desc
limit 1;
