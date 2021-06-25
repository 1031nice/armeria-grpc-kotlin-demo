package com.example.grpc

import com.example.grpc.SimpleServiceGrpc.getServiceDescriptor
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * Holder for Kotlin coroutine-based client and server APIs for com.example.grpc.SimpleService.
 */
object SimpleServiceGrpcKt {
  const val SERVICE_NAME: String = SimpleServiceGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = SimpleServiceGrpc.getServiceDescriptor()

  val saveRegionsMethod: MethodDescriptor<SimpleServiceOuterClass.SaveRegionsRequest,
      SimpleServiceOuterClass.SaveRegionsResponse>
    @JvmStatic
    get() = SimpleServiceGrpc.getSaveRegionsMethod()

  val saveAoisMethod: MethodDescriptor<SimpleServiceOuterClass.SaveAoisRequest,
      SimpleServiceOuterClass.SaveAoisResponse>
    @JvmStatic
    get() = SimpleServiceGrpc.getSaveAoisMethod()

  val getAoisByRegionIdMethod: MethodDescriptor<SimpleServiceOuterClass.GetAoisByRegionIdRequest,
      SimpleServiceOuterClass.GetAoisByRegionIdResponse>
    @JvmStatic
    get() = SimpleServiceGrpc.getGetAoisByRegionIdMethod()

  /**
   * A stub for issuing RPCs to a(n) com.example.grpc.SimpleService service as suspending
   * coroutines.
   */
  @StubFor(SimpleServiceGrpc::class)
  class SimpleServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<SimpleServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): SimpleServiceCoroutineStub =
        SimpleServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @return The single response from the server.
     */
    suspend fun saveRegions(request: SimpleServiceOuterClass.SaveRegionsRequest):
        SimpleServiceOuterClass.SaveRegionsResponse = unaryRpc(
      channel,
      SimpleServiceGrpc.getSaveRegionsMethod(),
      request,
      callOptions,
      Metadata()
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @return The single response from the server.
     */
    suspend fun saveAois(request: SimpleServiceOuterClass.SaveAoisRequest):
        SimpleServiceOuterClass.SaveAoisResponse = unaryRpc(
      channel,
      SimpleServiceGrpc.getSaveAoisMethod(),
      request,
      callOptions,
      Metadata()
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @return The single response from the server.
     */
    suspend fun getAoisByRegionId(request: SimpleServiceOuterClass.GetAoisByRegionIdRequest):
        SimpleServiceOuterClass.GetAoisByRegionIdResponse = unaryRpc(
      channel,
      SimpleServiceGrpc.getGetAoisByRegionIdMethod(),
      request,
      callOptions,
      Metadata()
    )}

  /**
   * Skeletal implementation of the com.example.grpc.SimpleService service based on Kotlin
   * coroutines.
   */
  abstract class SimpleServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for com.example.grpc.SimpleService.saveRegions.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun saveRegions(request: SimpleServiceOuterClass.SaveRegionsRequest):
        SimpleServiceOuterClass.SaveRegionsResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.example.grpc.SimpleService.saveRegions is unimplemented"))

    /**
     * Returns the response to an RPC for com.example.grpc.SimpleService.saveAois.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun saveAois(request: SimpleServiceOuterClass.SaveAoisRequest):
        SimpleServiceOuterClass.SaveAoisResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.example.grpc.SimpleService.saveAois is unimplemented"))

    /**
     * Returns the response to an RPC for com.example.grpc.SimpleService.getAoisByRegionId.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getAoisByRegionId(request: SimpleServiceOuterClass.GetAoisByRegionIdRequest):
        SimpleServiceOuterClass.GetAoisByRegionIdResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.example.grpc.SimpleService.getAoisByRegionId is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SimpleServiceGrpc.getSaveRegionsMethod(),
      implementation = ::saveRegions
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SimpleServiceGrpc.getSaveAoisMethod(),
      implementation = ::saveAois
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SimpleServiceGrpc.getGetAoisByRegionIdMethod(),
      implementation = ::getAoisByRegionId
    )).build()
  }
}
