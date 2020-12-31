drop table if exists userapp_topic;
drop table if exists post_topic;
drop table if exists post_userapp;
drop table if exists post;
drop table if exists topic;
drop table if exists userapp;
drop table if exists userrole;



create table userrole(
    id   int           not null,    primary key (id),
    name varchar(255) not null
)default charset=utf8 collate=utf8_spanish_ci;


create table topic(
    id   int           not null,    primary key (id),
    name varchar(255) not null
)default charset=utf8 collate=utf8_spanish_ci;


create table userapp(
    id         int           not null auto_increment,  primary key (id),
    first_name varchar(255)  not null,
    last_name  varchar(255)  not null,
    email      varchar(255)  not null,
    born_at    date          not null,
    role_id    int           not null default 1,       foreign key (role_id) references userrole (id),
    password   varchar(1023) not null,
    is_deleted bool          not null default false
)default charset=utf8 collate=utf8_spanish_ci;


create table userapp_topic(
    userapp_id  int not null, foreign key (userapp_id) references userapp (id),
    topic_id int not null,    foreign key (topic_id) references topic (id),
                              primary key (userapp_id, topic_id)
)default charset=utf8 collate=utf8_spanish_ci;


create table post(
    id                    int auto_increment not null,    primary key (id),
    title                 varchar(255)      not null,
    body                  text               not null,
    created_at            datetime default null,
    owner_id              int                not null,    foreign key (owner_id) references userapp (id),
    state                 enum ('edited','waiting','published','deleted'),
    published_at          datetime null default null,
    type                  enum ('general', 'flash', 'targeted', 'themed'),
    deleted_at            datetime null default null
)default charset=utf8 collate=utf8_spanish_ci;


create table post_topic(
    post_id  int not null,    foreign key (post_id) references post(id),
    topic_id int not null,    foreign key (topic_id) references topic(id),
                              primary key (post_id, topic_id)
)default charset=utf8 collate=utf8_spanish_ci;


create table post_userapp(
    post_id    int not null,  foreign key (post_id) references post(id),
    userapp_id int not null,  foreign key (userapp_id) references userapp(id),
                              primary key (post_id, userapp_id)
)default charset=utf8 collate=utf8_spanish_ci;


insert into userrole (id, name) values (1, 'user');
insert into userrole (id, name) values (2, 'admin');

insert into topic (id, name) values (1, 'java');
insert into topic (id, name) values (2, 'deportes');
insert into topic (id, name) values (3, 'historia');
insert into topic (id, name) values (4, 'política');
insert into topic (id, name) values (5, 'salud');
insert into topic (id, name) values (6, 'ciencia');
insert into topic (id, name) values (7, 'programación_web');
insert into topic (id, name) values (8, 'psicología');
insert into topic (id, name) values (9, 'gastronomía');
insert into topic (id, name) values (10, 'curiosidades');
insert into topic (id, name) values (11, 'covid-19');
insert into topic (id, name) values (12, 'educación');
insert into topic (id, name) values (13, 'cultura');
insert into topic (id, name) values (14, 'actualidad');

insert into userapp (id, first_name, last_name, email, born_at, role_id, password)
            values (1, 'Diego', 'Rodríguez Riera', 'i62rorid@uco.es', '1998-02-07', 2, '2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b');
insert into userapp (id, first_name, last_name, email, born_at, role_id, password)
            values (2, 'Ana', 'Navajas Fernández', 'i82nafea@uco.es', '2000-02-19', 2, '2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b');
insert into userapp (id, first_name, last_name, email, born_at, role_id, password)
            values (3, 'José Raúl', 'Romero Salguero', 'jrromero@uco.es', '1972-11-22', 1, '2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b');
insert into userapp (id, first_name, last_name, email, born_at, role_id, password)
            values (4, 'Carlos', 'Revuelto Quero', 'i82requc@uco.es', '2000-12-15', 1, '2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b');
insert into userapp (id, first_name, last_name, email, born_at, role_id, password)
            values (5, 'Aurora', 'Ramírez Quesada', 'i72raqua@uco.es', '1980-11-22', 1, '2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b');

insert into userapp_topic (userapp_id, topic_id) values (1, 1);
insert into userapp_topic (userapp_id, topic_id) values (1, 5);
insert into userapp_topic (userapp_id, topic_id) values (1, 14);
insert into userapp_topic (userapp_id, topic_id) values (2, 1);
insert into userapp_topic (userapp_id, topic_id) values (2, 2);
insert into userapp_topic (userapp_id, topic_id) values (2, 3);
insert into userapp_topic (userapp_id, topic_id) values (2, 7);
insert into userapp_topic (userapp_id, topic_id) values (2, 11);
insert into userapp_topic (userapp_id, topic_id) values (2, 12);
insert into userapp_topic (userapp_id, topic_id) values (3, 1);
insert into userapp_topic (userapp_id, topic_id) values (3, 4);
insert into userapp_topic (userapp_id, topic_id) values (3, 5);
insert into userapp_topic (userapp_id, topic_id) values (3, 13);
insert into userapp_topic (userapp_id, topic_id) values (4, 4);
insert into userapp_topic (userapp_id, topic_id) values (4, 5);
insert into userapp_topic (userapp_id, topic_id) values (4, 9);
insert into userapp_topic (userapp_id, topic_id) values (4, 10);
insert into userapp_topic (userapp_id, topic_id) values (4, 11);
insert into userapp_topic (userapp_id, topic_id) values (5, 7);
insert into userapp_topic (userapp_id, topic_id) values (5, 8);
insert into userapp_topic (userapp_id, topic_id) values (5, 9);
insert into userapp_topic (userapp_id, topic_id) values (5, 10);
insert into userapp_topic (userapp_id, topic_id) values (5, 11);
insert into userapp_topic (userapp_id, topic_id) values (5, 13);
insert into userapp_topic (userapp_id, topic_id) values (5, 14);


insert into post (id, title, body, created_at, owner_id, state, published_at, type, deleted_at)
    values (
        1, 'Cosmopoética, Poetas del Mundo en Córdoba',
        'Cada año, en otoño, tiene lugar en Córdoba el Festival Internacional de Poesía Cosmopoética, un evento cultural que propone un amplio programa diseñado para atraer a un público diverso, en el que no falta la música, las conversaciones con narradores, los ',
        '2020-11-18 14:47:29', 2, 'published', null, 'general', null
    );

insert into post (id, title, body, created_at, owner_id, state, published_at, type, deleted_at)
    values (
        2, 'Convocatoria ERASMUS+ 2021-2022',
        'La Universidad de Córdoba convoca PLAZAS para estancias de estudios de Grado y Máster en universidades europeas para el curso académico 2021/2022 en el marco de los acuerdos bilaterales firmados entre la Universidad de Córdoba y sus universidades socias.',
        '2020-11-10 14:50:40', 3, 'published', null, 'targeted', null
    );
insert into post_userapp (post_id, userapp_id) values (2, 1);
insert into post_userapp (post_id, userapp_id) values (2, 2);
insert into post_userapp (post_id, userapp_id) values (2, 4);


insert into post (id, title, body, created_at, owner_id, state, published_at, type, deleted_at)
    values (
        3, 'El Covid suma otros 11 muertos en Córdoba en una jornada con 235 positivos',
        'La pandemia de coronavirus sigue avanzando en la provincia de Córdoba, con unos datos que este viernes son buenos y malos a la vez. La mala noticia es que se incrementa de manera notable el número de víctimas mortales en la provincia. En total, y según lo',
        '2020-11-19 14:53:35', 5, 'published', null, 'themed', null
    );
insert into post_topic (post_id, topic_id) values (3, 11);
insert into post_topic (post_id, topic_id) values (3, 14);


insert into post (id, title, body, created_at, owner_id, state, published_at, type, deleted_at)
    values (
        4, 'MEDIA MARKT Black FRIDAY 2020',
        '¿Buscas lo último en tecnología e informática? Entonces ¡no te pierdas las ofertas del Black Friday y Cyber Monday 2020 de Media Markt! Encuentra todos los descuentos en el catálogo de ofertas Media Markt actual: Media Markt - Black Friday vigente a parti',
        '2020-11-19 14:56:39', 4, 'waiting', '2020-11-24 14:56:43', 'flash', '2020-12-28 14:56:48'
    );

insert into post (id, title, body, created_at, owner_id, state, published_at, type, deleted_at)
    values (
        5, 'Fecha de entrega de práctica 2',
        'Estimados estudiantes,  como sabéis, la próxima semana se inicia la práctica 3. El primer día (próximo lunes/martes) será de explicación (servlets y problema de prácticas).   Dadas las circunstancias que estamos viviendo, donde la presencialidad se está c',
        '2020-11-09 14:58:21', 3, 'published', null, 'general', null
    );