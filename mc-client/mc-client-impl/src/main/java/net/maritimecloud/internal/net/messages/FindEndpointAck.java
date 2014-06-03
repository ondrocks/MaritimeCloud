package net.maritimecloud.internal.net.messages;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.maritimecloud.core.message.Message;
import net.maritimecloud.core.message.MessageParser;
import net.maritimecloud.core.message.MessageReader;
import net.maritimecloud.core.message.MessageSerializers;
import net.maritimecloud.core.message.MessageWriter;
import net.maritimecloud.core.message.ValueParser;
import net.maritimecloud.internal.message.util.Hashing;
import net.maritimecloud.internal.message.util.MessageHelper;

public class FindEndpointAck implements Message, net.maritimecloud.internal.net.messages.spi.ReplyMessage {

    /** A message parser that can create new instances of this class. */
    public static final MessageParser<FindEndpointAck> PARSER = new Parser();

    /** Hey */
    private Long messageId;

    /** Hey */
    private Long latestReceivedId;

    /** Hey */
    private Long messageAck;

    /** Hey */
    private final List<String> remoteIDS;

    /** Hey */
    private Integer errorCode;

    /** Creates a new FindEndpointAck. */
    public FindEndpointAck() {
        remoteIDS = new java.util.ArrayList<>();
    }

    /**
     * Creates a new FindEndpointAck by reading from a message reader.
     *
     * @param reader
     *            the message reader
     */
    FindEndpointAck(MessageReader reader) throws IOException {
        this.messageId = reader.readInt64(1, "messageId", null);
        this.latestReceivedId = reader.readInt64(2, "latestReceivedId", null);
        this.messageAck = reader.readInt64(3, "messageAck", null);
        this.remoteIDS = MessageHelper.readList(reader, 4, "remoteIDS", ValueParser.STRING);
        this.errorCode = reader.readInt32(5, "errorCode", null);
    }

    /**
     * Creates a new FindEndpointAck by copying an existing.
     *
     * @param instance
     *            the instance to copy all fields from
     */
    FindEndpointAck(FindEndpointAck instance) {
        this.messageId = instance.messageId;
        this.latestReceivedId = instance.latestReceivedId;
        this.messageAck = instance.messageAck;
        this.remoteIDS = MessageHelper.immutableCopy(instance.remoteIDS);
        this.errorCode = instance.errorCode;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result = 31 + Hashing.hashcode(this.messageId);
        result = 31 * result + Hashing.hashcode(this.latestReceivedId);
        result = 31 * result + Hashing.hashcode(this.messageAck);
        result = 31 * result + Hashing.hashcode(this.remoteIDS);
        return 31 * result + Hashing.hashcode(this.errorCode);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof FindEndpointAck) {
            FindEndpointAck o = (FindEndpointAck) other;
            return Objects.equals(messageId, o.messageId) &&
                   Objects.equals(latestReceivedId, o.latestReceivedId) &&
                   Objects.equals(messageAck, o.messageAck) &&
                   Objects.equals(remoteIDS, o.remoteIDS) &&
                   Objects.equals(errorCode, o.errorCode);
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void writeTo(MessageWriter w) throws IOException {
        w.writeInt64(1, "messageId", messageId);
        w.writeInt64(2, "latestReceivedId", latestReceivedId);
        w.writeInt64(3, "messageAck", messageAck);
        w.writeList(4, "remoteIDS", remoteIDS);
        w.writeInt32(5, "errorCode", errorCode);
    }

    public Long getMessageId() {
        return messageId;
    }

    public boolean hasMessageId() {
        return messageId != null;
    }

    public FindEndpointAck setMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public Long getLatestReceivedId() {
        return latestReceivedId;
    }

    public boolean hasLatestReceivedId() {
        return latestReceivedId != null;
    }

    public FindEndpointAck setLatestReceivedId(Long latestReceivedId) {
        this.latestReceivedId = latestReceivedId;
        return this;
    }

    public Long getMessageAck() {
        return messageAck;
    }

    public boolean hasMessageAck() {
        return messageAck != null;
    }

    public FindEndpointAck setMessageAck(Long messageAck) {
        this.messageAck = messageAck;
        return this;
    }

    public List<String> getRemoteIDS() {
        return java.util.Collections.unmodifiableList(remoteIDS);
    }

    public boolean hasRemoteIDS() {
        return remoteIDS != null;
    }

    public FindEndpointAck addRemoteIDS(String remoteIDS) {
        java.util.Objects.requireNonNull(remoteIDS, "remoteIDS is null");
        this.remoteIDS.add(remoteIDS);
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public boolean hasErrorCode() {
        return errorCode != null;
    }

    public FindEndpointAck setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /** Returns a JSON representation of this message */
    public String toJSON() {
        return MessageSerializers.writeToJSON(this);
    }

    /**
     * Creates a message of this type from a JSON throwing a runtime exception if the format of the message does not match
     */
    public static FindEndpointAck fromJSON(CharSequence c) {
        return MessageSerializers.readFromJSON(PARSER, c);
    }

    /** {@inheritDoc} */
    @Override
    public FindEndpointAck immutable() {
        return new Immutable(this);
    }

    /** A parser for parsing instances of FindEndpointAck. */
    static class Parser extends MessageParser<FindEndpointAck> {

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck parse(MessageReader reader) throws IOException {
            return new FindEndpointAck(reader);
        }
    }

    /** An immutable version of FindEndpointAck. */
    static class Immutable extends FindEndpointAck {

        /**
         * Creates a new Immutable instance.
         *
         * @param instance
         *            the instance to make an immutable copy of
         */
        Immutable(FindEndpointAck instance) {
            super(instance);
        }

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck immutable() {
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck setMessageId(Long messageId) {
            throw new UnsupportedOperationException("Instance is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck setLatestReceivedId(Long latestReceivedId) {
            throw new UnsupportedOperationException("Instance is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck setMessageAck(Long messageAck) {
            throw new UnsupportedOperationException("Instance is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck addRemoteIDS(String remoteIDS) {
            throw new UnsupportedOperationException("Instance is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public FindEndpointAck setErrorCode(Integer errorCode) {
            throw new UnsupportedOperationException("Instance is immutable");
        }
    }
}
