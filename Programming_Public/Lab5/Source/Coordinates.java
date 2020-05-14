package com.nsi.lab5.commandwork;

    /**
    * Coordinates of Users
    */
    public class Coordinates implements Comparable<Coordinates> {
        /**
         * x Coordinate
         */
        private int x; //Значение поля должно быть больше -678
        /**
         * y Coordinate
         */
        private Integer y; //Поле не может быть null
        public Coordinates(String in){
            String[] str = in.split(",",2);
            x = Integer.parseInt(str[0]);
            y = Integer.parseInt(str[1]);

        }

        /**
         *
         * @return Coordinates
         */
        @Override
        public String toString() {
            return (" x = " + x + "; y = " + y);
        }

        /**
         * Matches Worker's Name
         * @return Coordinates in csv format
         *
         */
        public String tocsv() {
            return (x + "," + y);
        }

        /**
         * Matches Worker's Name
         * @return Compares Coordinates
         */
        @Override
        public int compareTo(Coordinates o) {
            if(o.x == this.x && o.y ==this.y) return 0;
            if(o.x > this.x && o.y >this.y) return  1;
            if(o.x < this.x && o.y < this.y) return -1;
            return -1;

        }

        @Override
        public boolean equals(Object obj) {
            Coordinates o = (Coordinates) obj;
            return (o.x ==  this.x && o.y.equals(this.y));
        }
    }

