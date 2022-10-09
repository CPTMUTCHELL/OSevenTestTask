insert into campaign(name) values ('campaign1');
insert into token(campaign_id, token_value) values (1,'123456');
insert into token(campaign_id, token_value) values (1,'abcdef');
insert into token(campaign_id, token_value) values (1,'213467');
insert into token(campaign_id, token_value) values (1,'a4r56g');
insert into provider(campaign_id,name) values (1,'provider1');
insert into provider(campaign_id,name) values (1,'provider2');
insert into provider(campaign_id,name,url) values (1,'newProvider','https://test.com/getProvier/newProvider');
insert into product(provider_id,product,price) values (1,'milk',4.5);
insert into product(provider_id,product,price) values (2,'coffee',5);


insert into product(provider_id,product,price) values (3,'coffeeFromNewProvider',2);
insert into product(provider_id,product,price) values (3,'milkFromNewProvider',3);