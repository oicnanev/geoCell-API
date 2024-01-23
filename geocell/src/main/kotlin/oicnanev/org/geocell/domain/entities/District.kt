/*
geocell-# \d geocell_district
                   Table "public.geocell_district"
   Column   |          Type          | Collation | Nullable | Default
------------+------------------------+-----------+----------+---------
 id         | character varying(20)  |           | not null |
 district   | character varying(100) |           | not null |
 polygon    | geometry(Polygon,4326) |           |          |
 country_id | character varying(100) |           |          |
Indexes:
    "geocell_district_pkey" PRIMARY KEY, btree (id)
    "geocell_district_country_id_e7acfaed" btree (country_id)
    "geocell_district_country_id_e7acfaed_like" btree (country_id varchar_pattern_ops)
    "geocell_district_id_4cabf366_like" btree (id varchar_pattern_ops)
    "geocell_district_polygon_2a9b8197_id" gist (polygon)
Foreign-key constraints:
    "geocell_district_country_id_e7acfaed_fk_geocell_country_name" FOREIGN KEY (country_id) REFERENCES geocell_country(name) DEFERRABLE INITIALLY DEFERRED
Referenced by:
    TABLE "geocell_county" CONSTRAINT "geocell_county_district_id_51ce031e_fk_geocell_district_id" FOREIGN KEY (district_id) REFERENCES geocell_district(id) DEFERRABLE INITIALLY DEFERRED
 */
package main.kotlin.oicnanev.org.geocell.domain.entities

data class District(
    val id: String,
    val district: String,
    val polygon: PolygonZ?,
    val countryId: String?
)
