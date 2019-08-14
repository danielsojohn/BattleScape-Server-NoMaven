var npc = null;

cs = new NCombatScript() {
    /* @Override */
    setNpcHook: function(n) {
        npc = n;
    },

    /* @Override */
    canBeAggressiveHook: function(entity) {
        if (!(entity instanceof Player)) {
            return true;
        }
        if (npc.getId() == 1610 && (entity.getEquipment().getWeaponId() == 2417
                || entity.getEquipment().getCapeId() == 2414 || entity.getEquipment().getCapeId() == 13333
                || entity.getEquipment().getCapeId() == 21795 || entity.getEquipment().getCapeId() == 21780)) {
            return false;
        } else if (npc.getId() == 1611 && (entity.getEquipment().getWeaponId() == 2415
                || entity.getEquipment().getCapeId() == 2412 || entity.getEquipment().getCapeId() == 13331
                || entity.getEquipment().getCapeId() == 21791 || entity.getEquipment().getCapeId() == 21776)) {
            return false;
        } else if (npc.getId() == 1612 && (entity.getEquipment().getWeaponId() == 2416
                || entity.getEquipment().getCapeId() == 2413 || entity.getEquipment().getCapeId() == 13335
                || entity.getEquipment().getCapeId() == 21793 || entity.getEquipment().getCapeId() == 21784)) {
            return false;
        }
    }
};
