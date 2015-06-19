create table komoditi(
idkomoditi int,
namakomoditi varchar(50),
primary key(idkomoditi)
)

create table member(
username varchar(50),
nama varchar(100),
password varchar(50)
primary key(username)
)

create table tanaman(
idtanaman int,
namatanaman varchar(50),
gambar varchar(100),
masapanen int,
deskripsi varchar(1500),
idkomoditi int,
primary key(idtanaman)
)