/* Copyright (c) 2011 Danish Maritime Authority.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.maritimecloud.mms.server.connection.client;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

import net.maritimecloud.core.id.MaritimeId;

import org.cakeframework.container.lifecycle.RunOnStart;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

/**
 *
 * @author Kasper Nielsen
 */

public class ClientManager implements Iterable<Client> {

    private final ConcurrentHashMap<String, Client> clients = new ConcurrentHashMap<>();

    final ClientManagerStatistics statistics = new ClientManagerStatistics(this);

    public void forEachTarget(Consumer<Client> consumer) {
        clients.forEachValue(10, requireNonNull(consumer));
    }

    public Client get(MaritimeId id) {
        return clients.get(id.toString());
    }

    public Client getOrCreate(MaritimeId id) {
        return clients.computeIfAbsent(id.toString(), key -> new Client(this, id));
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<Client> iterator() {
        return Collections.unmodifiableCollection(clients.values()).iterator();
    }

    public Stream<Client> parallelStream() {
        return clients.values().parallelStream();
    }

    @RunOnStart
    public void start(MetricRegistry metrics) {
        metrics.register(MetricRegistry.name("clients", "size"), (Gauge<Integer>) clients::size);
    }

    /**
     * @return the statistics
     */
    public ClientManagerStatistics statistics() {
        return statistics;
    }

    public Stream<Client> stream() {
        return clients.values().stream();
    }
}