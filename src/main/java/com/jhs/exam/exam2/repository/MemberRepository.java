package com.jhs.exam.exam2.repository;

import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.mysqliutil.MysqlUtil;
import com.jhs.mysqliutil.SecSql;

public class MemberRepository implements ContainerComponent {
	public void init() {
		
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.loginId = ?", loginId);
		sql.append("LIMIT 1");
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

	public void join(String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", email = ?", email);
		sql.append(", cellphoneNo = ?", cellphoneNo);
		
		MysqlUtil.insert(sql);
		
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.name = ?", name);
		sql.append("AND M.email = ?", email);
		sql.append("LIMIT 1");
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

	public Member getMemberByLoginIdAndEmail(String loginId, String email) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.loginId = ?", loginId);
		sql.append("AND M.email = ?", email);
		sql.append("LIMIT 1");
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

	public void setTemporaryPw(String temporaryPw, String loginId) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET temporaryPw = ?", temporaryPw);
		sql.append("WHERE loginId = ?", loginId);
		sql.append("LIMIT 1");
		
		MysqlUtil.update(sql);
	}

	public String getTemporaryPw(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT temporaryPw");
		sql.append("FROM member AS M");
		sql.append("WHERE M.loginId = ?", loginId);
		sql.append("LIMIT 1");
		
		return MysqlUtil.selectRowStringValue(sql);
	}

	public void changeLoginPwToTemporaryPw(String loginId, String temporaryPw) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET loginPw = ?", temporaryPw);
		sql.append(", temporaryPw = null");
		sql.append("WHERE loginId = ?", loginId);
		sql.append("LIMIT 1");
		
		MysqlUtil.update(sql);
	}

	public void modifyMemberInfo(String name, String nickname, String email, String cellphoneNo, int loginedMemberId) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET name = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", email = ?", email);
		sql.append(", cellphoneNo = ?", cellphoneNo);
		sql.append("WHERE id = ?", loginedMemberId);
		
		MysqlUtil.update(sql);
	}

	public Member getMemberById(int loginedMemberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.id = ?", loginedMemberId);
		sql.append("LIMIT 1");
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

	public void modifyMemberPw(int loginedMemberId, String newLoginPw) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET loginPw = ?", newLoginPw);
		sql.append("WHERE id = ?", loginedMemberId);
		
		MysqlUtil.update(sql);
	}

}
