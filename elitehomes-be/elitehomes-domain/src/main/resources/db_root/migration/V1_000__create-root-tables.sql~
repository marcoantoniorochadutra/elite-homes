    create database elite_homes_root;

    create table elite_homes_root.real_estate (
        version smallint default 0 not null,
        created_at timestamp not null,
        id integer unsigned not null auto_increment,
        tenant_key varchar(12) not null,
        company_reg_number varchar(50) not null,
        creci varchar(50) not null,
        name varchar(50) not null,
        address json not null,
        contact json not null,
        primary key (id)
    ) engine=InnoDB;

    alter table elite_homes_root.real_estate
       add constraint uk_real_estate_company_reg_number unique (company_reg_number);