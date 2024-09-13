
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

    create table address (
        id integer unsigned not null auto_increment,
        number varchar(10),
        neighborhood varchar(150),
        city varchar(255),
        complement varchar(255),
        country varchar(255),
        description varchar(255),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table attachment (
        attachment_id integer unsigned,
        id integer unsigned not null auto_increment,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table owner (
        version smallint default 0 not null,
        created_at timestamp not null,
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
        num_bathroom integer check (num_bathroom<=15),
        num_bedroom integer check (num_bedroom<=15),
        num_suite integer check (num_suite<=15),
        parking_spaces integer check (parking_spaces<=15),
        type tinyint unsigned not null check (type between 0 and 24),
        value float(53),
        version smallint default 0 not null,
        address_id integer unsigned not null,
        created_at timestamp not null,
        id integer unsigned not null auto_increment,
        owner_id integer unsigned not null,
        title varchar(50) not null,
        description varchar(255),
        value_description varchar(255),
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

    alter table users 
       add constraint uk_user_email unique (email);

    alter table users 
       add constraint UK_4ai7rrtrvwtgtqavv8okpxrul unique (user_details_id);

    alter table attachment 
       add constraint fk_attachment_property 
       foreign key (attachment_id) 
       references property (id);

    alter table property 
       add constraint fk_address_property 
       foreign key (address_id) 
       references address (id);

    alter table property 
       add constraint fk_owner_property 
       foreign key (owner_id) 
       references owner (id);

    alter table users 
       add constraint fk_user_details 
       foreign key (user_details_id) 
       references user_details (id);

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

    create table address (
        id integer unsigned not null auto_increment,
        number varchar(10),
        neighborhood varchar(150),
        city varchar(255),
        complement varchar(255),
        country varchar(255),
        description varchar(255),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table attachment (
        attachment_id integer unsigned,
        id integer unsigned not null auto_increment,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table owner (
        version smallint default 0 not null,
        created_at timestamp not null,
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
        num_bathroom integer check (num_bathroom<=15),
        num_bedroom integer check (num_bedroom<=15),
        num_suite integer check (num_suite<=15),
        parking_spaces integer check (parking_spaces<=15),
        type tinyint unsigned not null check (type between 0 and 24),
        value float(53),
        version smallint default 0 not null,
        address_id integer unsigned not null,
        created_at timestamp not null,
        id integer unsigned not null auto_increment,
        owner_id integer unsigned not null,
        title varchar(50) not null,
        description varchar(255),
        value_description varchar(255),
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

    alter table users 
       add constraint uk_user_email unique (email);

    alter table users 
       add constraint UK_4ai7rrtrvwtgtqavv8okpxrul unique (user_details_id);

    alter table attachment 
       add constraint fk_attachment_property 
       foreign key (attachment_id) 
       references property (id);

    alter table property 
       add constraint fk_address_property 
       foreign key (address_id) 
       references address (id);

    alter table property 
       add constraint fk_owner_property 
       foreign key (owner_id) 
       references owner (id);

    alter table users 
       add constraint fk_user_details 
       foreign key (user_details_id) 
       references user_details (id);

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

    create table address (
        id integer unsigned not null auto_increment,
        number varchar(10),
        neighborhood varchar(150),
        city varchar(255),
        complement varchar(255),
        country varchar(255),
        description varchar(255),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table attachment (
        attachment_id integer unsigned,
        id integer unsigned not null auto_increment,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table owner (
        version smallint default 0 not null,
        created_at timestamp not null,
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
        num_bathroom integer check (num_bathroom<=15),
        num_bedroom integer check (num_bedroom<=15),
        num_suite integer check (num_suite<=15),
        parking_spaces integer check (parking_spaces<=15),
        type tinyint unsigned not null check (type between 0 and 24),
        value float(53),
        version smallint default 0 not null,
        address_id integer unsigned not null,
        created_at timestamp not null,
        id integer unsigned not null auto_increment,
        owner_id integer unsigned not null,
        title varchar(50) not null,
        description varchar(255),
        value_description varchar(255),
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

    alter table users 
       add constraint uk_user_email unique (email);

    alter table users 
       add constraint UK_4ai7rrtrvwtgtqavv8okpxrul unique (user_details_id);

    alter table attachment 
       add constraint fk_attachment_property 
       foreign key (attachment_id) 
       references property (id);

    alter table property 
       add constraint fk_address_property 
       foreign key (address_id) 
       references address (id);

    alter table property 
       add constraint fk_owner_property 
       foreign key (owner_id) 
       references owner (id);

    alter table users 
       add constraint fk_user_details 
       foreign key (user_details_id) 
       references user_details (id);

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

    create table address (
        id integer unsigned not null auto_increment,
        number varchar(10),
        neighborhood varchar(150),
        city varchar(255),
        complement varchar(255),
        country varchar(255),
        description varchar(255),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table attachment (
        attachment_id integer unsigned,
        id integer unsigned not null auto_increment,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table owner (
        version smallint default 0 not null,
        created_at timestamp not null,
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
        num_bathroom integer check (num_bathroom<=15),
        num_bedroom integer check (num_bedroom<=15),
        num_suite integer check (num_suite<=15),
        parking_spaces integer check (parking_spaces<=15),
        type tinyint unsigned not null check (type between 0 and 24),
        value float(53),
        version smallint default 0 not null,
        address_id integer unsigned not null,
        created_at timestamp not null,
        id integer unsigned not null auto_increment,
        owner_id integer unsigned not null,
        title varchar(50) not null,
        description varchar(255),
        value_description varchar(255),
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

    alter table users 
       add constraint uk_user_email unique (email);

    alter table users 
       add constraint UK_4ai7rrtrvwtgtqavv8okpxrul unique (user_details_id);

    alter table attachment 
       add constraint fk_attachment_property 
       foreign key (attachment_id) 
       references property (id);

    alter table property 
       add constraint fk_address_property 
       foreign key (address_id) 
       references address (id);

    alter table property 
       add constraint fk_owner_property 
       foreign key (owner_id) 
       references owner (id);

    alter table users 
       add constraint fk_user_details 
       foreign key (user_details_id) 
       references user_details (id);
