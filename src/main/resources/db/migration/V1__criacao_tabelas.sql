

CREATE TABLE marca (
                       id bigserial NOT NULL,
                       ativo bool NULL,
                       atualizado_em timestamp NULL,
                       criado_em timestamp NULL,
                       fipe_id int8 NULL,
                       name varchar(255) NULL,
                       CONSTRAINT marca_pkey PRIMARY KEY (id)
);

CREATE TABLE modelo (
      id bigserial NOT NULL,
      ativo bool NULL,
      atualizado_em timestamp NULL,
      criado_em timestamp NULL,
      fipe_id int8 NULL,
      name varchar(255) NULL,
       marca_id int8 NULL,
       CONSTRAINT modelo_pkey PRIMARY KEY (id),
       CONSTRAINT fk_modelo_marca_id FOREIGN KEY (marca_id) REFERENCES marca(id)
);



CREATE TABLE veiculo (
                         id bigserial NOT NULL,
                         ativo bool NULL,
                         atualizado_em timestamp NULL,
                         criado_em timestamp NULL,
                         ano int8 NULL,
                         data_cadastro timestamp NULL,
                         placa varchar(255) NULL,
                         preco float8 NULL,
                         preco_anuncio float8 NULL,
                         modelo_id int8 NULL,
                         CONSTRAINT veiculo_pkey PRIMARY KEY (id),
                         CONSTRAINT fk_veiculo_modelo_id FOREIGN KEY (modelo_id) REFERENCES modelo(id)
);
