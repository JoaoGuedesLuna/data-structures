package dev.guedes.datastructures.util.collection.map;

import dev.guedes.datastructures.util.collection.Collection;
import dev.guedes.datastructures.util.collection.internal.nodes.EntryNode;
import dev.guedes.datastructures.util.collection.set.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract class for {@link Map} test classes.
 *
 * @author João Guedes
 */
abstract class MapTest {
    protected abstract Map<String, Integer> createMap();

    @Test
    void size_ShouldReturnZero_WhenMapIsEmpty() {
        assertEquals(0, createMap().size());
    }

    @Test
    void size_ShouldIncrease_WhenEntriesAreAdded() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("b", 2);

        assertEquals(2, map.size());
    }

    @Test
    void size_ShouldNotIncrease_WhenValueIsReplaced() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("a", 2);

        assertEquals(1, map.size());
    }

    @Test
    void size_ShouldDecrease_WhenEntryIsRemoved() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.remove("a");

        assertEquals(0, map.size());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenMapIsEmpty() {
        assertTrue(createMap().isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnFalse_WhenMapHasElements() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertFalse(map.isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenAllElementsAreRemoved() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.remove("a");

        assertTrue(map.isEmpty());
    }

    @Test
    void put_ShouldThrowException_WhenValueIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.put("a", null));
    }

    @Test
    void put_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.put(null, 1));
    }

    @Test
    void put_ShouldInsertEntry_WhenKeyIsNew() {
        Map<String, Integer> map = createMap();

        Integer old = map.put("a", 1);

        assertNull(old);
        assertEquals(1, map.size());
        assertEquals(1, map.get("a"));
    }

    @Test
    void put_ShouldReplaceValue_WhenKeyAlreadyExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        Integer old = map.put("a", 2);

        assertEquals(1, old);
        assertEquals(2, map.get("a"));
        assertEquals(1, map.size());
    }

    @Test
    void put_ShouldHandleHashCollision_WhenDifferentKeysHaveSameHash() {
        Map<String, Integer> map = createMap();

        String key1 = "FB";
        String key2 = "Ea";

        map.put(key1, 1);
        map.put(key2, 2);

        assertEquals(1, map.get(key1));
        assertEquals(2, map.get(key2));
        assertEquals(2, map.size());
    }

    @Test
    void replaceWithOldValue_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.replace(null, 1, 2));
    }

    @Test
    void replaceWithOldValue_ShouldThrowException_WhenOldValueIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.replace("a", null, 2));
    }

    @Test
    void replaceWithOldValue_ShouldThrowException_WhenNewValueIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.replace("a", 1, null));
    }

    @Test
    void replaceWithOldValue_ShouldReturnTrue_WhenMatchOccurs() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertTrue(map.replace("a", 1, 2));
        assertEquals(2, map.get("a"));
    }

    @Test
    void replaceWithOldValue_ShouldReturnFalse_WhenKeyDoesNotMatch() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertFalse(map.replace("b", 2, 3));
        assertEquals(1, map.get("a"));
    }

    @Test
    void replaceWithOldValue_ShouldReturnFalse_WhenOldValueDoesNotMatch() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertFalse(map.replace("a", 2, 3));
        assertEquals(1, map.get("a"));
    }

    @Test
    void replace_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.replace(null, 1));
    }

    @Test
    void replace_ShouldThrowException_WhenOldValueIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.replace("a", null));
    }

    @Test
    void replace_ShouldReturnOldValue_WhenKeyExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        Integer old = map.replace("a", 2);

        assertEquals(1, old);
        assertEquals(2, map.get("a"));
    }

    @Test
    void replace_ShouldReturnNull_WhenKeyDoesNotExist() {
        assertNull(createMap().replace("a", 1));
    }

    @Test
    void removeByKeyAndValue_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.remove(null, 1));
    }

    @Test
    void removeByKeyAndValue_ShouldThrowException_WhenOldValueIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.remove("a", null));
    }

    @Test
    void removeByKeyAndValue_ShouldReturnTrue_WhenMatchExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertTrue(map.remove("a", 1));
        assertFalse(map.containsKey("a"));
    }

    @Test
    void removeByKeyAndValue_ShouldReturnFalse_WhenKeyDoesNotMatch() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertFalse(map.remove("b", 2));
        assertTrue(map.containsKey("a"));
    }

    @Test
    void removeByKeyAndValue_ShouldReturnFalse_WhenValueDoesNotMatch() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertFalse(map.remove("a", 2));
        assertTrue(map.containsKey("a"));
    }

    @Test
    void removeByKey_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.remove(null));
    }

    @Test
    void removeByKey_ShouldReturnValue_WhenKeyExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        Integer removed = map.remove("a");

        assertEquals(1, removed);
        assertFalse(map.containsKey("a"));
    }

    @Test
    void remove_ShouldHandleCollisionChainCorrectly_WhenRemovingOneElement() {
        Map<String, Integer> map = createMap();

        String key1 = "FB";
        String key2 = "Ea";

        map.put(key1, 1);
        map.put(key2, 2);

        map.remove(key1);

        assertNull(map.get(key1));
        assertEquals(2, map.get(key2));
        assertEquals(1, map.size());
    }

    @Test
    void removeByKey_ShouldReturnNull_WhenKeyDoesNotExist() {
        assertNull(createMap().remove("a"));
    }

    @Test
    void get_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @Test
    void get_ShouldReturnValue_WhenKeyExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertEquals(1, map.get("a"));
    }

    @Test
    void get_ShouldReturnNull_WhenKeyDoesNotExist() {
        assertNull(createMap().get("a"));
    }

    @Test
    void containsKey_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.containsKey(null));
    }

    @Test
    void containsKey_ShouldReturnTrue_WhenKeyExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertTrue(map.containsKey("a"));
    }

    @Test
    void containsKey_ShouldReturnFalse_WhenKeyDoesNotExist() {
        assertFalse(createMap().containsKey("a"));
    }

    @Test
    void containsValue_ShouldThrowException_WhenKeyIsNull() {
        Map<String, Integer> map = createMap();

        assertThrows(NullPointerException.class, () -> map.containsValue(null));
    }

    @Test
    void containsValue_ShouldReturnTrue_WhenValueExists() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);

        assertTrue(map.containsValue(1));
    }

    @Test
    void containsValue_ShouldReturnFalse_WhenValueDoesNotExists() {
        Map<String, Integer> map = createMap();

        assertFalse(map.containsValue(1));
    }

    @Test
    void keySet_ShouldReturnEmptySet_WhenMapIsEmpty() {
        Map<String, Integer> map = createMap();

        Set<String> keys = map.keySet();

        assertTrue(keys.isEmpty());
        assertEquals(0, keys.size());
    }

    @Test
    void keySet_ShouldContainAllKeys_WhenMapHasElements() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("b", 2);

        Set<String> keys = map.keySet();

        assertEquals(2, keys.size());
        assertTrue(keys.contains("a"));
        assertTrue(keys.contains("b"));
    }

    @Test
    void values_ShouldReturnEmptyCollection_WhenMapIsEmpty() {
        Map<String, Integer> map = createMap();

        Collection<Integer> values = map.values();

        assertTrue(values.isEmpty());
        assertEquals(0, values.size());
    }

    @Test
    void values_ShouldContainAllValues_WhenMapHasElements() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("b", 2);

        Collection<Integer> values = map.values();

        assertEquals(2, values.size());
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
    }

    @Test
    void entrySet_ShouldReturnEmptySet_WhenMapIsEmpty() {
        Map<String, Integer> map = createMap();

        Set<?> entries = map.entrySet();

        assertTrue(entries.isEmpty());
        assertEquals(0, entries.size());
    }

    @Test
    void entrySet_ShouldContainEntries_WhenMapHasElements() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("b", 2);

        Set<EntryNode<String, Integer>> entrySet = map.entrySet();

        assertEquals(2, entrySet.size());

        entrySet.forEach(entry -> {
            assertTrue(map.containsKey(entry.getKey()));
            assertTrue(map.containsValue(entry.getValue()));
        });
    }

    @Test
    void toString_ShouldReturnEmptyRepresentation_WhenMapIsEmpty() {
        assertEquals("{}", createMap().toString());
    }

    @Test
    void toString_ShouldContainEntries_WhenMapHasElements() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("b", 2);

        String result = map.toString();

        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("a"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("2"));
    }

    @Test
    void clear_ShouldRemoveAllEntries_WhenMapHasElements() {
        Map<String, Integer> map = createMap();

        map.put("a", 1);
        map.put("b", 2);

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    void clear_ShouldKeepMapEmpty_WhenMapIsAlreadyEmpty() {
        Map<String, Integer> map = createMap();

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }
}
