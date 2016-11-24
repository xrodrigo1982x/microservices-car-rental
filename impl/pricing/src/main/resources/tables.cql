create table car (
    id text,
    dailyPrice decimal,
    primary key (id)
);

create table coupom (
    id text,
    type text,
    discount decimal,
    primary key (id)
);

create table offer(
    id text,
    price decimal,
    primary key (id)
);