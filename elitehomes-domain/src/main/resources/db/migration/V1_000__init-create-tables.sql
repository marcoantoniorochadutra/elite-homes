
    create table address (
        id integer unsigned not null auto_increment,
        number varchar(10),
        neighborhood varchar(150),
        city varchar(255),
        complement varchar(255),
        country varchar(255),
        details varchar(255),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table owner (
        id integer unsigned not null auto_increment,
        email varchar(100) not null,
        city varchar(255),
        country varchar(255),
        name varchar(255),
        national_registry varchar(255),
        phone varchar(255),
        state varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table property (
        goal tinyint unsigned not null check (goal between 0 and 1),
        num_bathroom integer,
        num_bedroom integer,
        num_suite integer,
        parking_spaces integer,
        type tinyint unsigned not null check (type between 0 and 24),
        value float(53),
        address_id integer unsigned not null,
        created_at timestamp null,
        id integer unsigned not null auto_increment,
        owner_id integer unsigned not null,
        details varchar(255),
        value_details varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table real_estate (
        version smallint default 0 not null,
        address_id integer unsigned not null,
        created_at datetime(6) not null,
        id integer unsigned not null auto_increment,
        company_reg_number varchar(50) not null,
        creci varchar(50) not null,
        contact json not null,
        primary key (id)
    ) engine=InnoDB;

    create table user_details (
        active bit not null,
        id integer unsigned not null auto_increment,
        locale varchar(20) not null,
        refresh_token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table users (
        id bigint not null auto_increment,
        user_details_id integer unsigned not null,
        email varchar(120) not null,
        hash_pass varchar(120) not null,
        name varchar(120) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table property
       add constraint UK_5ly66b153lksl8njtd39hj6mq unique (address_id);

    alter table real_estate
       add constraint UK_k958w4nr51ao4xj4llm72yhy2 unique (address_id);

    alter table users
       add constraint uk_user_email unique (email);

    alter table users
       add constraint UK_4ai7rrtrvwtgtqavv8okpxrul unique (user_details_id);

    alter table property
       add constraint fk_address_property
       foreign key (address_id)
       references address (id);

    alter table property
       add constraint fk_owner_property
       foreign key (owner_id)
       references owner (id);

    alter table real_estate
       add constraint fk_address_real_estate
       foreign key (address_id)
       references address (id);

    alter table users
       add constraint fk_user_details
       foreign key (user_details_id)
       references user_details (id);
