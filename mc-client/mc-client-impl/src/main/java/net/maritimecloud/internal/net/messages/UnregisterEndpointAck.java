package net.maritimecloud.internal.net.messages;

import java.io.IOException;
import java.util.Objects;

import net.maritimecloud.core.message.Message;
import net.maritimecloud.core.message.MessageParser;
import net.maritimecloud.core.message.MessageReader;
import net.maritimecloud.core.message.MessageSerializers;
import net.maritimecloud.core.message.MessageWriter;
import net.maritimecloud.internal.message.util.Hashing;

public class UnregisterEndpointAck implements Message, net.maritimecloud.internal.net.messages.spi.ReplyMessage {

    /** A message parser that can create new instances of this class. */
    public static final MessageParser<UnregisterEndpointAck> PARSER = new Parser();

    /** Hey */
    private Long messageId;

    /** Hey */
    private Long latestReceivedId;

    /** Hey */
    private Long messageAck;

    /** Creates a new UnregisterEndpointAck. */
    public UnregisterEndpointAck() {}

    /**
     * Creates a new UnregisterEndpointAck by reading from a message reader.
     *
     * @param reader
     *            the message reader
     */
    UnregisterEndpointAck(MessageReader reader) throws IOException {
        this.messageId = reader.readInt64(1, "messageId", null);
        this.latestReceivedId = reader.readInt64(2, "latestReceivedId", null);
        this.messageAck = reader.readInt64(3, "messageAck", null);
    }

    /**
     * Creates a new UnregisterEndpointAck by copying an existing.
     *
     * @param instance
     *            the instance to copy all fields from
     */
    UnregisterEndpointAck(UnregisterEndpointAck instance) {
        this.messageId = instance.messageId;
        this.latestReceivedId = instance.latestReceivedId;
        this.messageAck = instance.messageAck;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result = 31 + Hashing.hashcode(this.messageId);
        result = 31 * result + Hashing.hashcode(this.latestReceivedId);
        return 31 * result + Hashing.hashcode(this.messageAck);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof UnregisterEndpointAck) {
            UnregisterEndpointAck o = (UnregisterEndpointAck) other;
            return Objects.equals(messageId, o.messageId) &&
                   Objects.equals(latestReceivedId, o.latestReceivedId) &&
                   Objects.equals(messageAck, o.messageAck);
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void writeTo(MessageWriter w) throws IOException {
        w.writeInt64(1, "messageId", messageId);
        w.writeInt64(2, "latestReceivedId", latestReceivedId);
        w.writeInt64(3, "messageAck", messageAck);
    }

    public Long getMessageId() {
        return messageId;
    }

    public boolean hasMessageId() {
        return messageId != null;
    }

    public UnregisterEndpointAck setMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public Long getLatestReceivedId() {
        return latestReceivedId;
    }

    public boolean hasLatestReceivedId() {
        return latestReceivedId != null;
    }

    public UnregisterEndpointAck setLatestReceivedId(Long latestReceivedId) {
        this.latestReceivedId = latestReceivedId;
        return this;
    }

    public Long getMessageAck() {
        return messageAck;
    }

    public boolean hasMessageAck() {
        return messageAck != null;
    }

    public UnregisterEndpointAck setMessageAck(Long messageAck) {
        this.messageAck = messageAck;
        return this;
    }

    /** Returns a JSON representation of this message */
    public String toJSON() {
        return MessageSerializers.writeToJSON(this);
    }

    /**
     * Creates a message of this type from a JSON throwing a runtime exception if the format of the message does not match
     */
    public static UnregisterEndpointAck fromJSON(CharSequence c) {
        return MessageSerializers.readFromJSON(PARSER, c);
    }

    /** {@inheritDoc} */
    @Override
    public UnregisterEndpointAck immutable() {
        return new Immutable(this);
    }

    /** A parser for parsing instances of UnregisterEndpointAck. */
    static class Parser extends MessageParser<UnregisterEndpointAck> {

        /** {@inheritDoc} */
        @Override
        public UnregisterEndpointAck parse(MessageReader reader) throws IOException {
            return new UnregisterEndpointAck(reader);
        }
    }

    /** An immutable version of UnregisterEndpointAck. */
    static class Immutable extends UnregisterEndpointAck {

        /**
         * Creates a new Immutable instance.
         *
         * @param instance
         *            the instance to make an immutable copy of
         */
        Immutable(UnregisterEndpointAck instance) {
            super(instance);
        }

        /** {@inheritDoc} */
        @Override
        public UnregisterEndpointAck immutable() {
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public UnregisterEndpointAck setMessageId(Long messageId) {
            throw new UnsupportedOperationException("Instance is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public UnregisterEndpointAck setLatestReceivedId(Long latestReceivedId) {
            throw new UnsupportedOperationException("Instance is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public UnregisterEndpointAck setMessageAck(Long messageAck) {
            throw new UnsupportedOperationException("Instance is immutable");
        }
    }
}
