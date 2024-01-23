/*
geocell-# \d geocell_enbgnb
                          Table "public.geocell_enbgnb"
   Column    |  Type   | Collation | Nullable |             Default
-------------+---------+-----------+----------+----------------------------------
 id          | bigint  |           | not null | generated by default as identity
 enb_gnb     | integer |           | not null |
 location_id | bigint  |           | not null |
Indexes:
    "geocell_enbgnb_pkey" PRIMARY KEY, btree (id)
    "geocell_enbgnb_location_id_8c4f5999" btree (location_id)
Foreign-key constraints:
    "geocell_enbgnb_location_id_8c4f5999_fk_geocell_location_id" FOREIGN KEY (location_id) REFERENCES geocell_location(id) DEFERRABLE INITIALLY DEFERRED
Referenced by:
    TABLE "geocell_cell" CONSTRAINT "geocell_cell_enb_gnb_id_f28334a6_fk_geocell_enbgnb_id" FOREIGN KEY (enb_gnb_id) REFERENCES geocell_enbgnb(id) DEFERRABLE INITIALLY DEFERRED

 */
package main.kotlin.oicnanev.org.geocell.domain.entities

data class EnbGnb(
    val id: Long?,
    val enbGnb: Int,
    val locationId: Long
)