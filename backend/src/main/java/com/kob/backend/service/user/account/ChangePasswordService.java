package com.kob.backend.service.user.account;

import java.util.Map;

public interface ChangePasswordService {
    Map<String, String> changePassword(String oldPassword, String newPassword);
}
