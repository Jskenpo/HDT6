/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hdt6;

/**
 *
 * @author jsken
 */
public interface IMap<K,V> {
    public void put(K key, V value);
    
    public V get(K key);
    
    public void delete(K key);
    
    public int size();
    
}
