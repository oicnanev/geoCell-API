/*
geocell-# \d geocell_country
                  Table "public.geocell_country"
 Column  |          Type          | Collation | Nullable | Default
---------+------------------------+-----------+----------+---------
 name    | character varying(100) |           | not null |
 code    | character varying(4)   |           |          |
 polygon | geometry(Polygon,4326) |           |          |
 flag    | character varying(100) |           |          |
Indexes:
    "geocell_country_pkey" PRIMARY KEY, btree (name)
    "geocell_country_name_b2be7d8b_like" btree (name varchar_pattern_ops)
    "geocell_country_polygon_6a2cda57_id" gist (polygon)
Referenced by:
    TABLE "geocell_district" CONSTRAINT "geocell_district_country_id_e7acfaed_fk_geocell_country_name" FOREIGN KEY (country_id) REFERENCES geocell_country(name) DEFERRABLE INITIALLY DEFERRED
    TABLE "geocell_mccmnc" CONSTRAINT "geocell_mccmnc_country_id_8fe6cc8c_fk_geocell_country_name" FOREIGN KEY (country_id) REFERENCES geocell_country(name) DEFERRABLE INITIALLY DEFERRED
 */
package main.kotlin.oicnanev.org.geocell.domain.entities

data class Country(
    val name: String,
    val code: String,
    val polygon: PolygonZ?,
    val flag: String?
)

