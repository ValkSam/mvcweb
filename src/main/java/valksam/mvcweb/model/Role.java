package valksam.mvcweb.model;

        import valksam.mvcweb.LoggerWrapper;

public enum Role {
    ROLE_USER(1),
    ROLE_ADMIN(0);

    private int roleIdx;
    private static LoggerWrapper LOG = LoggerWrapper.get(Role.class);

    Role(int idx) {
        this.roleIdx = idx;
    }

    public static Role getRole(int idx) {
        for (Role role : Role.values()) {
            if (role.roleIdx == idx) return role;
        }
        throw LOG.getIllegalArgumentException("неверный номер роли пользователя :" + idx);
    }

    public Integer getIdx() {
        return roleIdx;
    }
}