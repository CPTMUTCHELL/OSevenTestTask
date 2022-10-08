insert into campaign(name) values ('campaign1');
insert into token(campaign_id, token_value) values (1,'123456');
insert into token(campaign_id, token_value) values (1,'abcdef');
insert into token(campaign_id, token_value) values (1,'213467');
insert into token(campaign_id, token_value) values (1,'a4r56g');
insert into provider(campaign_id,name,price,product) values (1,'provider1',5.4,'milk');
insert into provider(campaign_id,name,price,product) values (1,'provider2',4,'coffee');