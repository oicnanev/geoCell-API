package main.kotlin.oicnanev.org.geocell.repository.jdbi.mappers

import main.kotlin.oicnanev.org.geocell.domain.TokenValidationInfo
import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet
import java.sql.SQLException

class TokenValidationInfoMapper : ColumnMapper<TokenValidationInfo> {
    @Throws(SQLException::class)
    override fun map(rs: ResultSet, columnNumber: Int, ctx: StatementContext?): TokenValidationInfo =
        TokenValidationInfo(rs.getString(columnNumber))
}
