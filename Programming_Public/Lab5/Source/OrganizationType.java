package com.nsi.lab5.commandwork;

/**
 * Enum's of Organization Type's
 */
public enum OrganizationType {
    PUBLIC {
        @Override
        public String toString() {
            return "PUBLIC";
        }
    },
    GOVERNMENT {
        @Override
        public String toString() {
            return "GOVERNMENT";
        }
    },
    PRIVATE_LIMITED_COMPANY {
        @Override
        public String toString() {
            return "PRIVATE_LIMITED_COMPANY";
        }
    };

    /**
     *
     * @param i - entered org Type in String format
     * @return returns Enum format
     */
    public static OrganizationType getOrgType(String i) {
        switch (i) {
            case "PUBLIC": {
                return PUBLIC;
            }
            case "GOVERNMENT": {
                return GOVERNMENT;
            }
            case "PRIVATE_LIMITED_COMPANY": {
                return PRIVATE_LIMITED_COMPANY;
            }
        }
        ;
        return null;
    }

    ;
}