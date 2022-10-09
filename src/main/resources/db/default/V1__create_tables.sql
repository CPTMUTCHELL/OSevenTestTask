create table campaign(id serial primary key,name varchar, deactivated boolean default false);
create table token(id serial primary key, campaign_id integer references campaign (id), token_value text CONSTRAINT lengthMustBe6 CHECK (char_length(token_value) = 6), deactivated boolean default false);
create table provider(id serial primary key, campaign_id integer references campaign (id), name text, deactivated boolean default false,url varchar);
create table product(id serial primary key, provider_id integer references provider (id),  product text, price numeric)