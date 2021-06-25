package com.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: RegionsService.proto")
public final class RegionsServiceGrpc {

  private RegionsServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.RegionsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest,
      com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse> getSaveRegionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "saveRegions",
      requestType = com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest.class,
      responseType = com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest,
      com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse> getSaveRegionsMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest, com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse> getSaveRegionsMethod;
    if ((getSaveRegionsMethod = RegionsServiceGrpc.getSaveRegionsMethod) == null) {
      synchronized (RegionsServiceGrpc.class) {
        if ((getSaveRegionsMethod = RegionsServiceGrpc.getSaveRegionsMethod) == null) {
          RegionsServiceGrpc.getSaveRegionsMethod = getSaveRegionsMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest, com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "saveRegions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RegionsServiceMethodDescriptorSupplier("saveRegions"))
              .build();
        }
      }
    }
    return getSaveRegionsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RegionsServiceStub newStub(io.grpc.Channel channel) {
    return new RegionsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RegionsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RegionsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RegionsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RegionsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RegionsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void saveRegions(com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveRegionsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveRegionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest,
                com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse>(
                  this, METHODID_SAVE_REGIONS)))
          .build();
    }
  }

  /**
   */
  public static final class RegionsServiceStub extends io.grpc.stub.AbstractStub<RegionsServiceStub> {
    private RegionsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegionsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegionsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegionsServiceStub(channel, callOptions);
    }

    /**
     */
    public void saveRegions(com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveRegionsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RegionsServiceBlockingStub extends io.grpc.stub.AbstractStub<RegionsServiceBlockingStub> {
    private RegionsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegionsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegionsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegionsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse saveRegions(com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSaveRegionsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RegionsServiceFutureStub extends io.grpc.stub.AbstractStub<RegionsServiceFutureStub> {
    private RegionsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegionsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegionsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegionsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse> saveRegions(
        com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveRegionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_REGIONS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RegionsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RegionsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_REGIONS:
          serviceImpl.saveRegions((com.example.grpc.RegionsServiceOuterClass.SaveRegionsRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.RegionsServiceOuterClass.SaveRegionsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RegionsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RegionsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.RegionsServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RegionsService");
    }
  }

  private static final class RegionsServiceFileDescriptorSupplier
      extends RegionsServiceBaseDescriptorSupplier {
    RegionsServiceFileDescriptorSupplier() {}
  }

  private static final class RegionsServiceMethodDescriptorSupplier
      extends RegionsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RegionsServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RegionsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RegionsServiceFileDescriptorSupplier())
              .addMethod(getSaveRegionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
