package main.kotlin.oicnanev.org.geocell.repository.jdbi.mappers

import main.kotlin.oicnanev.org.geocell.domain.PasswordValidationInfo
import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet
import java.sql.SQLException

class PasswordValidationInfoMapper :ColumnMapper<PasswordValidationInfo> {
    @Throws(SQLException::class)
    override fun map(rs: ResultSet, columnNumber: Int, ctx: StatementContext?): PasswordValidationInfo =
        PasswordValidationInfo(rs.getString(columnNumber))
}
