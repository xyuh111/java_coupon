package com.web3n.passbook.mapper;

import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import com.web3n.passbook.constant.Constants;
import com.web3n.passbook.vo.User;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by macro on UserRowMapper.
 * HBase User Row To User Object
 **/
public class UserRowMapper implements RowMapper<User> {
    private static byte[] FAMILY_B = Constants.UserTable.FAMILY_B.getBytes();
    private static byte[] NAME = Constants.UserTable.NAME.getBytes();
    private static byte[] AGE = Constants.UserTable.AGE.getBytes();
    private static byte[] SEX = Constants.UserTable.SEX.getBytes();

    private static byte[] FAMILY_O = Constants.UserTable.FAMILY_O.getBytes();
    private static byte[] PHONE = Constants.UserTable.PHONE.getBytes();
    private static byte[] ADDRESS = Constants.UserTable.ADDRESS.getBytes();

    @Override
    public User mapRow(Result result, int i) throws Exception {
        User.BaseInfo baseInfo = new User.BaseInfo(
                Bytes.toString(result.getValue(FAMILY_B, NAME)),
                Bytes.toInt(result.getValue(FAMILY_B, AGE)),
                Bytes.toString(result.getValue(FAMILY_B, SEX))
        );
        User.OtherInfo otherInfo = new User.OtherInfo(
                Bytes.toString(result.getValue(FAMILY_O, PHONE)),
                Bytes.toString(result.getValue(FAMILY_O, ADDRESS))
        );
        return new User(Bytes.toLong(result.getRow()), baseInfo, otherInfo);
    }
}
