INSERT INTO marca (id, name, fipe_id) VALUES ('1', 'FIAT', 21);
INSERT INTO marca (id, name, fipe_id) VALUES ('2', 'FORD', 22);
INSERT INTO marca (id, name, fipe_id) VALUES ('3', 'CHEVROLET', 23);


INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('1', '147 C/ CL', '437','1');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('2', 'ARGO 1.0 6V Flex.','8315', '1');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('3', 'Doblo ESSENCE 1.8 Flex 16V 5p', '5536', '1');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('4', 'Belina GL 1.8 / 1.6','657', '2');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('5', 'EcoSport XL 1.6/ 1.6
Flex 8V 5p', '680', '2');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('6', 'Fiesta SEL Style 1.6
16V Flex Mec. 5p', '7754', '2');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('7', 'Astra Eleg. 2.0 MPFI
FlexPower 8V 5p Aut', '940', '3');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('8', 'Celta Life 1.0 MPFI
VHC 8V 3p', '997', '3');
INSERT INTO modelo (id, name, fipe_id, marca_id) VALUES ('9', 'Meriva Joy 1.8
MPFI 8V FlexPower', '1093', '3');



INSERT INTO veiculo (id, preco,data_cadastro,placa,ano,preco_anuncio,modelo_id) VALUES (4, 20000, '2003-03-03','XXXX-EE',2004,200,1);
INSERT INTO veiculo (id, preco,data_cadastro,placa,ano,preco_anuncio,modelo_id) VALUES (5, 50000, '2003-03-07','XXXX-FF',2011,300,1);
INSERT INTO veiculo (id, preco,data_cadastro,placa,ano,preco_anuncio,modelo_id) VALUES (6, 60000, '2003-03-08','XXXX-GG',2007,400,2);
