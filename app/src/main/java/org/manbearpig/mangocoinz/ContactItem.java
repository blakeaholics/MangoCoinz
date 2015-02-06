package org.manbearpig.mangocoinz;

public class ContactItem {
        private String name;
        private String user;
        private String total;
        
        // Constructor for the contacts class
        public ContactItem(String name, String user, String total) {
                super();
                this.name = name;
                this.user = user;
                this.total = total;
        }
        
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getUser() {
                return user;
        }
        public void setUser(String user) {
                this.user = user;
        }
        public String getTotal() {
                return total;
        }
        public void setTotal(String total) {
                this.total = total;
        }
}