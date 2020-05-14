package com.nsi.lab5.commandwork;

import java.util.ArrayList;

/**
 * List that contains unique elements
 * @param <E>
 */
  public class  UniqList<E>  extends ArrayList<E> {
      private E e;

      public UniqList (){
          super();
      };

      public boolean ifuniq(E obj) {
          for (int i = 0; i < this.size(); i++) {
              if (this.get(i).equals(obj)) {
                  return false;
              }
          }
          return true;
      }

    /** Adds only if uique element
     *
     * @param o
     * @return Added or Not
     */
      @Override
      public boolean add(E o) {
          if(ifuniq(o) == true) {
              return super.add(o);}
          return false;
      }
  }






