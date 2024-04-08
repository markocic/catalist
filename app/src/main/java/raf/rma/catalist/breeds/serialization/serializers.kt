package raf.rma.catalist.breeds.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import raf.rma.catalist.breeds.model.Range

class CommaSeparatedListSerializer: KSerializer<List<String>> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "CommaSeparatedList",
        PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): List<String> {
        return decoder.decodeString().split(",").map { it.trim() }
    }

    override fun serialize(encoder: Encoder, value: List<String>) {
        encoder.encodeString(value.joinToString(", "))
    }

}

class RangeIntSerializer: KSerializer<Range<Int>> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "RangeInt",
        PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Range<Int> {
        val split = decoder.decodeString()
            .split("-")
            .map { it.trim().toInt() }
        return Range(from = split[0], to = split[1])
    }

    override fun serialize(encoder: Encoder, value: Range<Int>) {
        encoder.encodeString("${value.from} - ${value.to}")
    }
}

class BraindeadBoolSerializer : KSerializer<Boolean> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "BraindeadBoolSerializer",
        PrimitiveKind.INT
    )

    override fun deserialize(decoder: Decoder): Boolean = decoder.decodeInt() != 0

    override fun serialize(encoder: Encoder, value: Boolean) = encoder.encodeInt(
        if (value) 1 else 0
    )
}